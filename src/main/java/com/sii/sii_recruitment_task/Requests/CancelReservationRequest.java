package com.sii.sii_recruitment_task.Requests;

public class CancelReservationRequest {
    private final Long prelectionId;
    private final String login;

    public CancelReservationRequest(String login, Long prelectionId) {
        this.login = login;
        this.prelectionId = prelectionId;
    }

    public Long getPrelectionId() {
        return prelectionId;
    }

    public String getLogin() {
        return login;
    }

}
