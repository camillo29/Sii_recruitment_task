package com.sii.sii_recruitment_task.Responses.DTO;

import java.sql.Time;

public class PrelectionWithPercentsDTO extends PrelectionDTO{
    private final Double percents;

    public PrelectionWithPercentsDTO(Long id, Time startHour, String topic, Double percents) {
        super(id, startHour, topic);
        this.percents = percents;
    }

    public Double getPercents() {
        return percents;
    }
}
