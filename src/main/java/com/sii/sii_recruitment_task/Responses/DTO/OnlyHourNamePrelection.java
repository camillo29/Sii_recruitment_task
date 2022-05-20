package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class OnlyHourNamePrelection{
    private final Time startHour;
    private final String name;

    public OnlyHourNamePrelection(Time startHour, String name){
        this.startHour = startHour;
        this.name = name;
    }

    public Time getStartHour() {
        return startHour;
    }

    public String getName() {
        return name;
    }
}
