package com.sii.sii_recruitment_task.Service;

import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Requests.CancelReservationRequest;
import com.sii.sii_recruitment_task.Requests.MakeReservationRequest;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    void makeReservation(MakeReservationRequest request);
    void sendMail(String email, String content) throws Exception;
    void cancelReservation(CancelReservationRequest request) throws Exception;
    Map<Time, Long> getPrelectionsInterest();
    Long getReservationsFromPrelectionsByHour(Time hour);
    List<Prelection> getPrelectionFromPrelectionService();
    Map<String, Long> getTopicReservations();
    Long getReservationsFromPrelectionsByTopic(String topic);
    Long getTotalReservations();
}
