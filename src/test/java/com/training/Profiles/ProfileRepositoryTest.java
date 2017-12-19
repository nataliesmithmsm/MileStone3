package com.training.Profiles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
@AutoConfigureTestEntityManager
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    private Profile profile1, profile2, profile3;

    @Before
    public void setUp() {
        profile1 = createProfile("1", "111", "Natalie", "Smith");
        profile2 = createProfile("2", "110", "Carly", "Wise");
        profile3 = createProfile("3", "001", "Simone", "Conway");
    }

    @Test
    public void findProfileById_shouldReturnCorrectProfile() throws Exception {
        Profile result = profileRepository.findById(profile3.getId());
        assertEquals(profile3, result);
    }

    @Test
    public void findProfileByFirstName_shouldReturnCorrectProfile() throws Exception {
        List<Profile> profiles = profileRepository.findByFirstName(profile1.getFirstName());
        assertFalse(profiles.isEmpty());
        assertEquals(profile1, profiles.get(0));
    }

    @Test
    public void findByProfileID_shouldReturnCorrectProfile() throws Exception {
        Profile result = profileRepository.findByProfileID(profile2.getProfileID());
        assertEquals(profile2, result);
    }

    @Test
    public void shouldCreateNewProfile() throws Exception {
        Profile profile = new Profile(null, "testId", "firstName", "lastName");
        profile = profileRepository.save(profile);

        assertEquals(profile, profileRepository.findById(profile.getId()));
    }

    private Profile createProfile(String id, String profileID, String firstName, String lastName) {
        Profile profile = new Profile(id, profileID, firstName, lastName);
        return profileRepository.save(profile);
    }
}