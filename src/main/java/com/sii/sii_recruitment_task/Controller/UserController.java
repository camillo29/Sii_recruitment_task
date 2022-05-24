package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Requests.ChangeMailRequest;
import com.sii.sii_recruitment_task.Responses.DTO.UserDTO;
import com.sii.sii_recruitment_task.Responses.EntityResponse;
import com.sii.sii_recruitment_task.Responses.GetAllUsersResponse;
import com.sii.sii_recruitment_task.Responses.GetPrelectionsResponse;
import com.sii.sii_recruitment_task.Responses.Response;
import com.sii.sii_recruitment_task.Service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public Response getUsers(){
        return new GetAllUsersResponse(userService.getUsers());
    }

    @GetMapping("/{login}/prelections")
    public Response getUserPrelections(@NotNull @PathVariable(name = "login") String login) throws ResponseStatusException{
        User user = userService.findByLogin(login);
        if(user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user!");
        return new GetPrelectionsResponse(user.getPrelections());
    }

    @PatchMapping("/{login}/changeMail")
    public Response changeMail(@NotNull @PathVariable(name = "login") String login,
                               @NotNull @RequestBody ChangeMailRequest request)
            throws ResponseStatusException{
        if(request.getNewMail() == null || request.getOldMail() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty fields!");
        }
        User user = userService.changeMail(login, request);
        return new EntityResponse(new UserDTO(user.getId(), user.getLogin(), user.getEmail()));
    }



}
