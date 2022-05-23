package com.sii.sii_recruitment_task.Service;

import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Requests.ChangeMailRequest;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User findByLogin(String login);
    void validateUser(User user, String email, Time startHour) throws Exception;
    User save(User user);
    User changeMail(String login, ChangeMailRequest request) throws ResponseStatusException;
    Long getNumberOfUsers();
}
