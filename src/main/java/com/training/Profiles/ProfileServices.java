package com.training.Profiles;


import com.sun.tools.javac.sym.Profiles;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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


    public void addIDProfile(Profile profile) {

        if (profile.getFirstname() != null && profile.getLastname() != null) {
            String uniqueID = UUID.randomUUID().toString();
            profile.setID(uniqueID);

            profiles.add(profile);
            System.out.println(profile);

        }
    }
}
