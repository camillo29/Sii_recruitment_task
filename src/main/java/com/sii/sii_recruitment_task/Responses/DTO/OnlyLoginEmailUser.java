package com.sii.sii_recruitment_task.Responses.DTO;

public class OnlyLoginEmailUser {
    private final String login;
    private final String email;

    public OnlyLoginEmailUser(String login, String email){
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
