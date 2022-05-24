package com.sii.sii_recruitment_task.Tests;
import com.sii.sii_recruitment_task.Requests.CancelReservationRequest;
import com.sii.sii_recruitment_task.Requests.ChangeMailRequest;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
public class ReservationsControllerTests {
    @Autowired
    WebApplicationContext context;
    @Autowired
    MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void givenOKMakeReservationRequestWithExistingUser_whenMakeReservation_thenStatus201(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(9L, "login2", "email2@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenOKMakeReservationRequestWithNewUser_whenMakeReservation_thenStatus201(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(
                    3L,"login10", "email10@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongMakeReservationRequestWithWrongPrelection_whenMakeReservation_thenStatus404(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(
                    18L, "login1", "email1@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongMakeReservationRequestWithNoPlace_whenMakeReservation_thenStatus409(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(
                    1L, "login10", "email10@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isConflict())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongMakeReservationRequestWithNewUserWithUsedLogin_whenMakeReservation_thenStatus409(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(
                    9L,"login5", "email10@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isConflict())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongMakeReservationRequestWithAlreadyReservedHour_whenMakeReservation_thenStatus409(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(
                    2L, "login1", "email1@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isConflict())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongMakeReservationRequestWithEmptyFields_whenMakeReservation_thenStatus400(){
        try{
            MakeReservationRequest request = new MakeReservationRequest(5L, null, "email1@com.pl");
            mvc.perform(post("/reservations/makeReservation")
                    .contentType("application/json")
                    .content(new Gson().toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenOKCancelRequest_whenCancelReservation_thenStatus204(){
        try{
            mvc.perform(delete("/reservations/login1/cancelReservation?prelectionId=1"))
                    .andExpect(status().isNoContent())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongCancelRequestWithEmptyFields_whenCancelReservation_thenStatus400(){
        try{
            mvc.perform(delete("/reservations/login1/cancelReservation"))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongCancelRequestWithNotExistingUser_whenCancelReservation_thenStatus404(){
        try{
            mvc.perform(delete("/reservations/login10/cancelReservation?prelectionId=1"))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongCancelRequestWithNotExistingReservation_whenCancelReservation_thenStatus404(){
        try{
            mvc.perform(delete("/reservations/login1/cancelReservation?prelectionId=2"))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGetPrelectionsInterest_thenStatus200(){
        try{
            mvc.perform(get("/reservations/getPrelectionsInterest"))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGetTopicInterest_thenStatus200(){
        try{
            mvc.perform(get("/reservations/getTopicInterest"))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
