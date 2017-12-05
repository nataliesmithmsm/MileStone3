package com.training.Profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;

    @RequestMapping("/Profiles")
    public List<Profile> getAllProfiles(){
        return profileServices.getAllProfiles();
    }

    @RequestMapping("/Profiles/{ID}")
    public Profile getProfile(@PathVariable String ID){
        return profileServices.getProfile(ID);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Profiles")
    public void addTopic(@RequestBody Profile profile){
        profileServices.addProfile(profile); }



}
