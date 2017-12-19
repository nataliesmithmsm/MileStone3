package com.training.Profiles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServices {

    private final ProfileRepository profileRepository;

    private List<Profile> profiles = new ArrayList<>(Arrays.asList(

            new Profile("1", "001", "Natalie", "Smith"),
            new Profile("2", "002","Harry", "Potter"),
            new Profile("3","003", "Lucy", "Hale")
    ));

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public List<Profile> findAllLocalProfiles(){return profiles;}

    public Profile findLocalProfileByID (String profileID) {
        return this.profiles.stream().filter(profile -> profile.getProfileID().equals(profileID)).findFirst().get();
    }

    public void addLocalProfile(Profile profile) {
        this.profiles.add(profile);
    }

    public void generateAutomaticProfile(Profile profile) {

        if(profile.getFirstName() != null && profile.getLastName() != null){

            String uniqueID = UUID.randomUUID().toString();
            profile.setProfileID(uniqueID);

            profiles.add(profile);
            System.out.println(profile.toString());
        }
    }

    public List<Profile> findProfilesByFirstName(String firstName){
        return profileRepository.findByFirstName(firstName);
    }
    public void postIntoMongo(Profile profile) {
        profileRepository.save(profile);
    }

    public Profile findProfileById(String id) throws IOException {
        return profileRepository.findById(id);
    }

    public Profile findProfileByProfileId(String profileID){
        return profileRepository.findByProfileID(profileID);
    }
}




