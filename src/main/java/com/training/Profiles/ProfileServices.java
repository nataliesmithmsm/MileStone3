package com.training.Profiles;


import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProfileServices {

    private List<Profile> profiles = new ArrayList<>(Arrays.asList(

            new Profile("001", "Natalie", "Smith"),
            new Profile("002", "Harry", "Potter"),
            new Profile("003", "Lucy", "Hale")
    ));

    public List<Profile> getAllProfiles() {
        return profiles;
    }

    public Profile getProfile(String ID) {
        return profiles.stream().filter(profile -> profile.getID().equals(ID)).findFirst().get();
    }


    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

}
