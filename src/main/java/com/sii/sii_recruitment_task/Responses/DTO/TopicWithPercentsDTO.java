package com.sii.sii_recruitment_task.Responses.DTO;

public class TopicWithPercentsDTO {
    private final String topic;
    private final Double percents;

    public TopicWithPercentsDTO(String topic, Double percents){
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
