package com.training.Profiles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProfileController.class)
public class ProfileControllerTests {

    @MockBean
    private ProfileServices profileServices;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProfileRepository profileRepository;

    private Profile profile;

    private Profile profile1, profile2, profile3, profile4;

    @Before
    public void setUp() {
        profile1 = new Profile("1", "110", "Carly", "Wise");
        profile2 = new Profile("2", "002", "Natalie", "Smith");
        profile3 = new Profile("3", "003", "Mollie", "Wilcox");
        profile4 = new Profile("test", "test", "testName", "testLastName");
        String firstName = "testFirstName";
        String lastName = "lestLastName";
    }

    @Test
    public void shouldReturnAllProfiles() throws Exception {
        when(profileServices.getAllProfiles()).thenReturn(Arrays.asList(profile2, profile3));

        mockMvc.perform(get("/profiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("2"))
                .andExpect(jsonPath("$[0].profileID").value("002"))
                .andExpect(jsonPath("$[0].firstName").value("Natalie"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))

                .andExpect(jsonPath("$[1].id").value("3"))
                .andExpect(jsonPath("$[1].profileID").value("003"))
                .andExpect(jsonPath("$[1].firstName").value("Mollie"))
                .andExpect(jsonPath("$[1].lastName").value("Wilcox"));
    }

    @Test
    public void addLocalProfile() throws Exception {
    }

    @Test
    public void generateAutomaticIDProfile() throws Exception {
//        Profile profile5 = new Profile("","testID", "testname", "testSurname");
//
//        Mockito.when(profileServices.generateAutomaticProfile(profile5));
//       // String testName = userService.getUserName("SomeId");
//        Assert.assertEquals(profile5, profile);
    }

    @Test
    public void postProfileIntoMongoDb() throws Exception {
//        when(profileServices.postIntoMongo(profile4)).thenReturn(profileRepository.save(profile4))
//        mockMvc.perform(post("/addProfile").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }

    @Test
    public void findProfilesById() throws Exception {

        mockMvc.perform(post("/addProfile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void searchByProfileID_shouldReturnProfile() throws Exception {
        String profileID = "SOME_ID";
        Profile profile = new Profile("1", profileID, "Natalie", "Smith");
        when(profileServices.findProfileByProfileId(profileID)).thenReturn(profile);

        mockMvc.perform(get("/profiles/{profileID}",
                profileID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.profileID").value("SOME_ID"))
                .andExpect(jsonPath("$.firstName").value("Natalie"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    public void searchByProfileID_shouldThrowNotFoundException() throws Exception {
        String wrongID = "WRONG_ID";
        when(profileServices.findProfileByProfileId(wrongID)).thenReturn(null);

        mockMvc.perform(get("/profiles/{profileID}", wrongID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"error\":\"Profile not found\"}"));
    }

}