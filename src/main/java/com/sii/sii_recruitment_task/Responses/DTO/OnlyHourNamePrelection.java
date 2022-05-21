package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class OnlyHourNamePrelection{
    private Time startHour;
    private String name;

    public OnlyHourNamePrelection(Time startHour, String name){
        this.startHour = startHour;
        this.name = name;
    }

    public OnlyHourNamePrelection(){}

    public Time getStartHour() {
        return startHour;
    }

    public String getName() {
        return name;
    }
}
