package com.sii.sii_recruitment_task.Requests;

public class CancelReservationRequest {
    private Long prelectionId;
    private String login;

    public Long getPrelectionId() {
        return prelectionId;
    }

    public void setPrelectionId(Long prelectionId) {
        this.prelectionId = prelectionId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
