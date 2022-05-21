package com.sii.sii_recruitment_task.Service.Impl;

import com.sii.sii_recruitment_task.FileHandler;
import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Requests.CancelReservationRequest;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;
import com.sii.sii_recruitment_task.Responses.DTO.PrelectionWithPercents;
import com.sii.sii_recruitment_task.Responses.GetPrelectionsInterestResponse;
import com.sii.sii_recruitment_task.Service.PrelectionService;
import com.sii.sii_recruitment_task.Service.ReservationService;
import com.sii.sii_recruitment_task.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @Override
    public void cancelReservation(CancelReservationRequest request) throws Exception{
        User user = userService.findByLogin(request.getLogin());
        if(user == null){
            throw new Exception("No such user!");
        }
        user.getPrelections().removeIf(p -> p.getId().equals(request.getPrelectionId()));
        userService.save(user);
    }

    @Override
    public Map<Time,Long> getPrelectionsInterest(){
        HashMap<Time, Long> reservations = new HashMap<>();
        reservations.put(Time.valueOf("10:00:00"), getReservationsFromPrelectionsByHour(Time.valueOf("10:00:00")));
        reservations.put(Time.valueOf("12:00:00"), getReservationsFromPrelectionsByHour(Time.valueOf("12:00:00")));
        reservations.put(Time.valueOf("14:00:00"), getReservationsFromPrelectionsByHour(Time.valueOf("14:00:00")));
        return reservations;
    }

    @Override
    public Long getReservationsFromPrelectionsByHour(Time hour){
        Long reservations = 0L;
        reservations += prelectionService.getPrelectionsByHour(hour).stream().mapToInt(p->p.getUsers().size()).sum();
        return reservations;
    }

    @Override
    public List<Prelection> getPrelectionFromPrelectionService() {
        return prelectionService.getPrelections();
    }

    @Override
    public Map<String, Long> getTopicReservations(){
        HashMap<String, Long> reservations = new HashMap<>();
        reservations.put("Java", getReservationsFromPrelectionsByTopic("Java"));
        reservations.put("Python", getReservationsFromPrelectionsByTopic("Python"));
        reservations.put("C#", getReservationsFromPrelectionsByTopic("C#"));
        return reservations;
    }

    @Override
    public Long getReservationsFromPrelectionsByTopic(String topic){
        Long reservations = 0L;
        reservations += prelectionService.getPrelectionsByTopic(topic).stream().mapToInt(p->p.getUsers().size()).sum();
        return reservations;
    }

    @Override
    public Long getTotalReservations(){
        return (long) prelectionService.getPrelections().stream().mapToInt(p->p.getUsers().size()).sum();
    }

}
