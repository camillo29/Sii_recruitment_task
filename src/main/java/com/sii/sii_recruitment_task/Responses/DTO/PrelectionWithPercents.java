package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class PrelectionWithPercents extends OnlyHourNamePrelection{
    private final Double percents;

    public PrelectionWithPercents(Time startHour, String name, Double percents) {
        super(startHour, name);
        this.percents = percents;
    }

    public Double getPercents() {
        return percents;
    }
}
