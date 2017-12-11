package com.training.Profiles;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProfileRepository extends MongoRepository<Profile, String> {

    Profile findById(String id);

    List<Profile> findByFirstName(String firstName);
}
