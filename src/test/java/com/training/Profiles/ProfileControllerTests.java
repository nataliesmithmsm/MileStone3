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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Profile> profiles = new ArrayList<>(Arrays.asList());

    }

    @Test
    public void findAllProfilesInMongo_shouldReturnAllProfiles() throws Exception {
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
    public void addLocalProfile_shouldCreateProfile() throws Exception {
        Profile mockProfile = new Profile("test1234", "00test", "testfirstname", "testLastName");

      mockMvc.perform(post("/localProfiles/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\n" +
                       " \t\t\"id\": \"1\",\n" +
                       "        \"profileID\": \"001\",\n" +
                       "        \"firstName\": \"Natalie\",\n" +
                       "        \"lastName\": \"Smith\"\n" +
                       "    }"))
               .andExpect(status().isOk());
    }

    @Test
    public void generateAutomaticIDForProfile() throws Exception{

        mockMvc.perform(post("/localProfiles/AutoID")
        .contentType(MediaType.APPLICATION_JSON)
        .content(" {\n" +
                " \t    \"firstName\": \"Natalie\",\n" +
                "        \"lastName\": \"Smith\"\n" +
                "    }"))
                .andExpect(status().isOk());
    }

    @Test
    public void postProfileIntoMongoDb_shouldPostProfile() throws Exception {
         mockMvc.perform(post("/profiles/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \t\t\"id\": \"1\",\n" +
                        "        \"profileID\": \"001\",\n" +
                        "        \"firstName\": \"Natalie\",\n" +
                        "        \"lastName\": \"Smith\"\n" +
                        "    }"))
                .andExpect(status().isOk());

    }


    @Test
    public void searchByProfileID_shouldReturnProfile() throws Exception {
        String profileID = "SOME_ID";
        Profile profile = new Profile("1", profileID, "Natalie", "Smith");
        when(profileServices.findProfileByProfileId(profileID)).thenReturn(profile);

        mockMvc.perform(get("/profiles/search/{profileID}",
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

        mockMvc.perform(get("/profiles/search/{profileID}", wrongID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"error\":\"Profile not found\"}"));
    }

}