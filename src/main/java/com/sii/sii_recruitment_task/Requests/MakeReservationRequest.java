package com.sii.sii_recruitment_task.Requests;

public class MakeReservationRequest {
    private Long prelectionId;
    private String login;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
