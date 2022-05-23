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


@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/makeReservation")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeReservation(@NotNull @RequestBody MakeReservationRequest request) throws Exception{
        reservationService.makeReservation(request);
    }

    @DeleteMapping("/{login}/cancelReservation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(@PathVariable(name = "login") String login,
                                  @RequestParam(name = "prelectionId") Long id) throws Exception{
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
