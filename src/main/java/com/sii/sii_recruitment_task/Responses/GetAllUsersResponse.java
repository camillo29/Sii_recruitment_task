package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Responses.DTO.UserDTO;

import java.util.LinkedList;
import java.util.List;

public class GetAllUsersResponse extends Response {

    public GetAllUsersResponse(List<User> users){
        entities = new LinkedList<>();
        for(User u: users){
            UserDTO responseUser =
                    new UserDTO(u.getId(), u.getLogin(), u.getEmail());
            entities.add(responseUser);
        }
    }

    public List getEntities(){
        return entities;
    }
}
