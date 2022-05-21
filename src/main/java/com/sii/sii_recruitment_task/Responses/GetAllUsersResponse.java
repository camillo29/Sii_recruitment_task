package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Model.User;
import com.sii.sii_recruitment_task.Responses.DTO.OnlyLoginEmailUser;

import java.util.LinkedList;
import java.util.List;

public class GetAllUsersResponse extends Response {

    public GetAllUsersResponse(List<User> users){
        entities = new LinkedList<>();
        for(User u: users){
            OnlyLoginEmailUser responseUser =
                    new OnlyLoginEmailUser(u.getLogin(), u.getEmail());
            entities.add(responseUser);
        }
    }

    public List getEntities(){
        return entities;
    }
}
