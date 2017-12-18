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
    public List<Profile> findByFirstNameOrGetAllProfiles(@RequestParam(required = false) String firstName) {
        return firstName != null ? profileServices.findProfilesByFirstName(firstName) : profileServices.getAllProfiles();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllProfiles")
    public List<Profile> findAllLocalProfiles() {
        return profileServices.findAllLocalProfiles();
    }

//    @RequestMapping("/profiles/{ID}")
//    public Profile getProfile(@PathVariable String ID){
//        return profileServices.getProfile(ID);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/profiles")
    public void addLocalProfile(@RequestBody Profile profile) {
        profileServices.addLocalProfile(profile);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/profilesID")
    public void generateAutomaticIDProfile(@RequestBody Profile profile) {
        profileServices.generateAutomaticProfile(profile);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProfile")
    public void postProfileIntoMongoDb(@RequestBody Profile profile) {
        profileServices.postIntoMongo(profile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mongo/{id}")
    public Profile findProfilesById(@PathVariable("id") String id) throws IOException {
        Profile matchingIDSS = profileServices.findProfileById(id);
        System.out.println(matchingIDSS);
        return matchingIDSS;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profiles/{profileID}")
    public Profile searchByID(@PathVariable("profileID") String profileID, HttpServletResponse response) throws IOException {
        Profile matchedProfile = profileServices.findProfileByProfileId(profileID);
        return Optional.ofNullable(matchedProfile)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }
}
