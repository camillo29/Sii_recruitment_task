package com.sii.sii_recruitment_task.Requests;

public class MakeReservationRequest {
    private final Long prelectionId;
    private final String login;
    private final String email;

    public MakeReservationRequest(Long prelectionId, String login, String email) {
        this.prelectionId = prelectionId;
        this.login = login;
        this.email = email;
    }

    public Long getPrelectionId() {
        return prelectionId;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

}
