package com.sii.sii_recruitment_task.Tests;

import com.sii.sii_recruitment_task.Requests.ChangeMailRequest;
import com.sii.sii_recruitment_task.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import com.google.gson.Gson;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
@Sql("/dataTest.sql")
public class UserControllerTests {
    @Autowired
    WebApplicationContext context;
    @Autowired
    MockMvc mvc;
    @Autowired
    UserService userService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void whenGetUsers_thenStatus200(){
        try {
            mvc.perform(get("/users/getUsers"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("id")));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenOKLogin_whenGetUserPrelections_thenStatus200(){
        try {
            mvc.perform(get("/users/login1/prelections"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("id")));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongLogin_whenGetUserPrelections_thenStatus404(){
        try {
            mvc.perform(get("/users/login10/prelections"))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenOKChangeMailRequest_whenChangeMail_thenStatus200(){
        try {
            ChangeMailRequest request = new ChangeMailRequest();
            request.setOldMail("email1@com.pl");
            request.setNewMail("newEmail1@com.pl");
            mvc.perform(patch("/users/login1/changeMail")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("newEmail1@com.pl")))
                    .andDo(print());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongChangeMailRequest_whenChangeMail_thenStatus409(){
        try {
            ChangeMailRequest request = new ChangeMailRequest();
            request.setOldMail("wrongEmail1@com.pl");
            request.setNewMail("newEmail1@com.pl");
            mvc.perform(patch("/users/login1/changeMail")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isConflict())
                    .andDo(print());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongLogin_whenChangeMail_thenStatus404(){
        try {
            ChangeMailRequest request = new ChangeMailRequest();
            request.setOldMail("email1@com.pl");
            request.setNewMail("newEmail1@com.pl");
            mvc.perform(patch("/users/login1500/changeMail")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        } catch(Exception e){
            e.printStackTrace();
        }
    }



}
