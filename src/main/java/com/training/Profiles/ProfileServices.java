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

//    public Profile getProfile(String ID) {
//        return this.profiles.stream().filter(profile -> profile.getId().equals(ID)).findFirst().get();
//    }

    public List<Profile> getByFirstName(String firstName){
        return profileRepository.findByFirstName(firstName);
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    public void generateAutomaticProfile(Profile profile) {

        if(profile.getFirstName() != null && profile.getLastName() != null){

            String uniqueID = UUID.randomUUID().toString();
            profile.setId(uniqueID);

            profiles.add(profile);
            System.out.println(profile.toString());
        }
    }

    public void postIntoMongo(Profile profile) {
        profileRepository.save(profile);
    }

    public Profile getDatafromMongo(String id) throws IOException {
        return profileRepository.findById(id);
    }

    public Profile findByID (String profileID){
        return profileRepository.findByProfileID(profileID);
    }
}




