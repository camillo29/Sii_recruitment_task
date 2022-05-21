package com.sii.sii_recruitment_task.Responses.DTO;

public class TopicWithPercents {
    private final String topic;
    private final Double percents;

    public TopicWithPercents(String topic, Double percents){
        this.topic = topic;
        this.percents = percents;
    }

    public String getTopic() {
        return topic;
    }

    public Double getPercents() {
        return percents;
    }
}
