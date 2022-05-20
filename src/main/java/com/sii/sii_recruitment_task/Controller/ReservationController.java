package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.FileHandler;
import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Repository.PrelectionRepository;
import com.sii.sii_recruitment_task.Repository.UserRepository;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrelectionRepository prelectionRepository;

    @PostMapping("/makeReservation")
    public void makeReservation(@RequestBody MakeReservationRequest request) throws Exception{
        Prelection prelection = prelectionRepository
                .findById(request.getPrelectionId())
                .orElseThrow(()->new Exception("No such prelection!"));
        if(prelection == null) return;
        if(prelection.getUsers().size()>=5){
            throw new Exception("Not enough place in this prelection");
        }

        User user = userRepository.findByLogin(request.getLogin());

        if(user == null) {
            user = new User(request.getLogin(), request.getEmail());
        }
        else if(!user.getEmail().equals(request.getEmail())){
            throw new Exception("Podany login jest już zajęty");
        }

        if(user.isGivenHourReserved(prelection.getStartHour())){
            throw new Exception("User have reservation for this hour!");
        }

        user.getPrelections().add(prelection);
        userRepository.save(user);
        sendMail(user.getEmail(),
                "Content: Registration for " + prelection.getName() +
                " at " + prelection.getStartHour() +
                " successfully made!");

    }

    private void sendMail(String email, String content) throws Exception{
        FileHandler f = new FileHandler();
        try{
            f.openAndCreateIfNotExists();
            f.writeToFile("Date:" + LocalDate.now());
            f.writeToFile("To:" + email);
            f.writeToFile(content);
        } catch(IOException e){
            e.printStackTrace();
            throw new Exception("Error while sending mail");
        } finally {
            f.close();
        }
    }

}
