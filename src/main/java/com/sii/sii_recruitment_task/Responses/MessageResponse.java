package com.sii.sii_recruitment_task.Responses;

public class MessageResponse extends Response{

    public MessageResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
