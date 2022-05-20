package com.sii.sii_recruitment_task.Service;

import com.sii.sii_recruitment_task.Requests.CancelReservationRequest;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;

public interface ReservationService {
    void makeReservation(MakeReservationRequest request);
    void sendMail(String email, String content) throws Exception;
    void cancelReservation(CancelReservationRequest request) throws Exception;
}
