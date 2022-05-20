package com.sii.sii_recruitment_task.Service;

import com.sii.sii_recruitment_task.Model.User;

import java.sql.Time;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User findByLogin(String login);
    void validateUser(User user, String email, Time startHour) throws Exception;
    User save(User user);
}
