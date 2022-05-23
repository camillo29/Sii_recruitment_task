package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Requests.CancelReservationRequest;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;
import com.sii.sii_recruitment_task.Responses.GetPrelectionsInterestResponse;
import com.sii.sii_recruitment_task.Responses.GetTopicInterestResponse;
import com.sii.sii_recruitment_task.Responses.Response;
import com.sii.sii_recruitment_task.Service.ReservationService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/makeReservation")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeReservation(@NotNull @RequestBody MakeReservationRequest request) throws Exception{
        if(request.getEmail()==null || request.getLogin()==null || request.getPrelectionId()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty fields!");
        }
        reservationService.makeReservation(request);
    }

    @DeleteMapping("/{login}/cancelReservation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(@NotNull @PathVariable(name = "login") String login,
                                  @NotNull @RequestParam(name = "prelectionId") Long id) throws Exception{
        reservationService.cancelReservation(new CancelReservationRequest(login, id));
    }

    @GetMapping("/getPrelectionsInterest")
    public Response getPrelectionsInterest(){
        return new GetPrelectionsInterestResponse(
                reservationService.getPrelectionFromPrelectionService(),
                reservationService.getPrelectionsInterest());
    }

    @GetMapping("/getTopicInterest")
    public Response getTopicInterestResponse(){
        return new GetTopicInterestResponse(
                reservationService.getTopicReservations(),
                reservationService.getTotalReservations());
    }

}
