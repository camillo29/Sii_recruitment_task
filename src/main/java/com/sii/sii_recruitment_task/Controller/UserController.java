package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Requests.ChangeMailRequest;
import com.sii.sii_recruitment_task.Requests.GetUserPrelectionsRequest;
import com.sii.sii_recruitment_task.Responses.GetAllUsersResponse;
import com.sii.sii_recruitment_task.Responses.GetPrelectionsResponse;
import com.sii.sii_recruitment_task.Responses.MessageResponse;
import com.sii.sii_recruitment_task.Responses.Response;
import com.sii.sii_recruitment_task.Service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public Response getUsers(){
        return new GetAllUsersResponse(userService.getUsers());
    }

    @GetMapping("/getUserPrelections")
    public Response getUserPrelections(@NotNull @RequestBody GetUserPrelectionsRequest request){
        return new GetPrelectionsResponse(userService.findByLogin(request.getLogin()).getPrelections());
    }

    @PatchMapping("/changeMail")
    public Response changeMail(@NotNull @RequestBody ChangeMailRequest request){
        String message;
        if(userService.changeMail(request)){
            message = "Email updated!";
        } else message = "Email update failed!";
        return new MessageResponse(message);
    }



}
