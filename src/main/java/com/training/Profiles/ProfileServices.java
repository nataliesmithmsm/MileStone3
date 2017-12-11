package com.training.Profiles;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.*;

@Service
@RequiredArgsConstructor
public class ProfileServices {

    private final ProfileRepository profileRepository;

    private List<Profile> profiles = new ArrayList<>(Arrays.asList(

            new Profile("001", "Natalie", "Smith"),
            new Profile("002", "Harry", "Potter"),
            new Profile("003", "Lucy", "Hale")
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

    public void postIntoMongo(Profile profile)
    {
//        MongoCollection<BasicDBObject> profiles = ConnetionToMongo.connection();
//
//        BasicDBObject profileDBObject = new BasicDBObject();
//
//        profileDBObject.put("id", profile.getId());
//        profileDBObject.put("firstName", profile.getFirstName());
//        profileDBObject.put("lastName", profile.getLastName());
//        profiles.insertOne(profileDBObject);
        profileRepository.save(profile);
    }


    public Profile getDatafromMongo(String id) throws IOException { //List<Profile>
//        ObjectMapper mapper = new ObjectMapper();
//        MongoCollection<BasicDBObject> allProfiles = ConnetionToMongo.connection();
//
//        List<Profile> MatchingID = new ArrayList<>();
//
//        BasicDBObject query = new BasicDBObject();
//        query.put("id", id);
//
//        MongoCursor<BasicDBObject> cursor = allProfiles.find(query).iterator();
//
//        while(cursor.hasNext()){
//
//            BasicDBObject resultID = cursor.next();
//
//            DBObject getMatchingId = (BasicDBObject) resultID.get("ProfileDetails");
//
//            String ID = (String) getMatchingId.get("id");
//            String firstName = (String) getMatchingId.get("firstName");
//            String lastName = (String) getMatchingId.get("lastName");
//
//            Profile profile = new Profile();
//            profile.setId(ID);
//            profile.setFirstName(firstName);
//            profile.setLastName(lastName);
//
//            System.out.println(profile.toString());
//            MatchingID.add(profile);
//        }
//        return MatchingID;
        return profileRepository.findById(id);
    }
}

//    MongoCollection<BasicDBObject> profiles = ConnetionToMongo.connection();
//    BasicDBObject profileDBObject = new BasicDBObject();
//
//        if (profile.getFirstName() != null && profile.getLastName() != null){
//
//                String uniqueID = UUID.randomUUID().toString();
//                profile.setId(uniqueID);
//                }
//                profileDBObject.put("ID", profile.getId());
//                profileDBObject.put("FirstName", profile.getFirstName());
//                profileDBObject.put("LastName", profile.getLastName());
//                profiles.insertOne(profileDBObject);


//        if (profile.isEmpty()) {
//            return;
//        }
//        String uniqueID = UUID.randomUUID().toString();
//        profile.setId(uniqueID);
//
//        profiles.add(profile);
//        System.out.println(profile);


// private final List<Profile> profiles;

//    public ProfileServices() {
//
//        Profile natalie = Profile.builder().id("001").firstName("Natalie").lastName("Smith").build();
//        Profile harry = Profile.builder().id("002").firstName("Harry").lastName("Potter").build();
//        Profile lucy = Profile.builder().id("001").firstName("Lucy").lastName("Hale").build();
//    }


