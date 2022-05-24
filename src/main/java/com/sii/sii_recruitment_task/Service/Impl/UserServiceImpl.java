package com.sii.sii_recruitment_task.Service.Impl;

import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Repository.UserRepository;
import com.sii.sii_recruitment_task.Requests.ChangeMailRequest;
import com.sii.sii_recruitment_task.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Override
    public void validateUser(User user, String email, Time startHour) throws ResponseStatusException{
        if(!user.getEmail().equals(email)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Podany login jest już zajęty");
        }
        if(user.isGivenHourReserved(startHour)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User have reservation for this hour!");
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User changeMail(String login, ChangeMailRequest request) throws ResponseStatusException{
        User user = findByLogin(login);
        if(user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user!");
        if(!user.getEmail().equals(request.getOldMail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Old mails are not equal!");
        }
        user.setEmail(request.getNewMail());
        save(user);
        return user;
    }

}
