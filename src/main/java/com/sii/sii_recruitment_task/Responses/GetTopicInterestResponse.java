package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Responses.DTO.TopicWithPercentsDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetTopicInterestResponse extends Response{

    public GetTopicInterestResponse(Map<String, Long> reservationsForTopic, Long totalReservations){
        entities = new LinkedList();
        for(Map.Entry<String, Long> entry: reservationsForTopic.entrySet()){
            Double percents = 0.0;
            if(totalReservations != 0){
                percents = ((double)entry.getValue()/(double)totalReservations)*100;
            }
            entities.add(new TopicWithPercentsDTO(
                    entry.getKey(),
                    percents));
        }
    }

    public List getEntities(){
        return entities;
    }
}
