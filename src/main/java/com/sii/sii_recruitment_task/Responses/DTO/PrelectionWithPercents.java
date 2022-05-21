package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class PrelectionWithPercents extends OnlyHourTopicPrelection{
    private final Double percents;

    public PrelectionWithPercents(Time startHour, String topic, Double percents) {
        super(startHour, topic);
        this.percents = percents;
    }

    public Double getPercents() {
        return percents;
    }
}
