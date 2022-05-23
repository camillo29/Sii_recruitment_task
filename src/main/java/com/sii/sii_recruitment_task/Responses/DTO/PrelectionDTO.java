package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class PrelectionDTO{
    private Long id;
    private Time startHour;
    private String topic;

    public PrelectionDTO(Long id, Time startHour, String topic){
        this.id = id;
        this.startHour = startHour;
        this.topic = topic;
    }

    public PrelectionDTO(){}

    public Time getStartHour() {
        return startHour;
    }

    public String getTopic() {
        return topic;
    }

    public Long getId() {
        return id;
    }
}
