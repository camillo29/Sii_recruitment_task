package com.sii.sii_recruitment_task.Responses;

public class EntityResponse extends Response{

    public EntityResponse(Object entity){
        this.entity = entity;
    }

    public Object getEntity(){
        return entity;
    }
}
