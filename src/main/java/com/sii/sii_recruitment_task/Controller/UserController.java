package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Repository.UserRepository;
import com.sii.sii_recruitment_task.Responses.GetAllUsersResponse;
import com.sii.sii_recruitment_task.Responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUsers")
    public Response getUsers(){
        return new GetAllUsersResponse(userRepository.findAll());
    }



}
