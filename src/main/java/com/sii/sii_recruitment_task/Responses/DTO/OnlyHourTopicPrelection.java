package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class OnlyHourTopicPrelection{
    private Time startHour;
    private String topic;

    public OnlyHourTopicPrelection(Time startHour, String topic){
        this.startHour = startHour;
        this.topic = topic;
    }

    public OnlyHourTopicPrelection(){}

    public Time getStartHour() {
        return startHour;
    }

    public String getTopic() {
        return topic;
    }
}
