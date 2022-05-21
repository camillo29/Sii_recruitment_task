package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Requests.CancelReservationRequest;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;
import com.sii.sii_recruitment_task.Responses.GetPrelectionsInterestResponse;
import com.sii.sii_recruitment_task.Responses.GetTopicInterestResponse;
import com.sii.sii_recruitment_task.Service.ReservationService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/makeReservation")
    public void makeReservation(@NotNull @RequestBody MakeReservationRequest request) throws Exception{
        reservationService.makeReservation(request);
    }

    @DeleteMapping("/cancelReservation")
    public void cancelReservation(@NotNull @RequestBody CancelReservationRequest request) throws Exception{
        reservationService.cancelReservation(request);
    }

    @GetMapping("/getPrelectionsInterest")
    public GetPrelectionsInterestResponse getPrelectionsInterest(){
        return new GetPrelectionsInterestResponse(
                reservationService.getPrelectionFromPrelectionService(),
                reservationService.getPrelectionsInterest());
    }

    @GetMapping("/getTopicInterest")
    public GetTopicInterestResponse getTopicInterestResponse(){
        return new GetTopicInterestResponse(
                reservationService.getTopicReservations(),
                reservationService.getTotalReservations());
    }

    /*
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
    }*/

}
