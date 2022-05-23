package com.sii.sii_recruitment_task.Responses.DTO;

public class UserDTO {
    private final Long id;
    private final String login;
    private final String email;

    public UserDTO(Long id, String login, String email){
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
