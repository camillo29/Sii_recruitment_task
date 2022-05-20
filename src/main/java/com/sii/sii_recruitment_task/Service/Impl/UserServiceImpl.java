package com.sii.sii_recruitment_task.Service.Impl;

import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Repository.UserRepository;
import com.sii.sii_recruitment_task.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void validateUser(User user, String email, Time startHour) throws Exception{
        if(!user.getEmail().equals(email)){
            throw new Exception("Podany login jest już zajęty");
        }
        if(user.isGivenHourReserved(startHour)){
            throw new Exception("User have reservation for this hour!");
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
