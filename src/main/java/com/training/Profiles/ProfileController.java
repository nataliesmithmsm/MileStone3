package com.training.Profiles;

import com.training.domain.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;

    @RequestMapping("/profiles")
    public List<Profile> getAllProfiles(@RequestParam(required = false) String firstName){
        return firstName != null ? profileServices.getByFirstName(firstName) : profileServices.getAllProfiles();
    }

//    @RequestMapping("/profiles/{ID}")
//    public Profile getProfile(@PathVariable String ID){
//        return profileServices.getProfile(ID);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/profiles")
    public void addProfile(@RequestBody Profile profile){
        profileServices.addProfile(profile); }

    @RequestMapping(method =  RequestMethod.POST, value = "/profilesID")
    public void automaticIDProfile(@RequestBody Profile profile){
        profileServices.generateAutomaticProfile(profile);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProfile")
    public void putProfileIntoMongo(@RequestBody Profile profile){
        profileServices.postIntoMongo(profile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mongo/{id}")
    public Profile getprofilesFromMongo(@PathVariable("id") String id) throws IOException {
        Profile matchingIDSS = profileServices.getDatafromMongo(id);
        System.out.println(matchingIDSS);
        return matchingIDSS;    }

    @RequestMapping(method = RequestMethod.GET, value = "/profiles/{profileID}")
    public Profile searchByID (@PathVariable("profileID") String profileID, HttpServletResponse response) throws  IOException{
        Profile matchedProfile = profileServices.findByID(profileID);
        return Optional.ofNullable(matchedProfile)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }
}
