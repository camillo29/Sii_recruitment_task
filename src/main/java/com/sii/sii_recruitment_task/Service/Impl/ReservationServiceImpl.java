package com.sii.sii_recruitment_task.Service.Impl;

import com.sii.sii_recruitment_task.FileHandler;
import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;
import com.sii.sii_recruitment_task.Service.PrelectionService;
import com.sii.sii_recruitment_task.Service.ReservationService;
import com.sii.sii_recruitment_task.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private UserService userService;
    @Autowired
    private PrelectionService prelectionService;

    @Override
    public void makeReservation(MakeReservationRequest request){
        try {
            Prelection prelection = prelectionService.findById(request.getPrelectionId());
            prelectionService.validatePrelection(prelection);
            User user = userService.findByLogin(request.getLogin());
            if(user == null) {
                user = new User(request.getLogin(), request.getEmail());
            }
            userService.validateUser(user, request.getEmail(), prelection.getStartHour());
            user.getPrelections().add(prelection);
            userService.save(user);
            sendMail(user.getEmail(),
                    "Content: Registration for " + prelection.getName() +
                            " at " + prelection.getStartHour() +
                            " successfully made!");
        } catch(Exception e){
            //CUSTOM EXCEPTION
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void sendMail(String email, String content) throws Exception{
        FileHandler f = new FileHandler();
        try{
            f.openAndCreateIfNotExists();
            f.writeToFile("Date:" + LocalDate.now());
            f.writeToFile("To:" + email);
            f.writeToFile(content);
        } catch(IOException e){
            throw new Exception("Error while sending mail");
        } finally {
            f.close();
        }
    }

}
