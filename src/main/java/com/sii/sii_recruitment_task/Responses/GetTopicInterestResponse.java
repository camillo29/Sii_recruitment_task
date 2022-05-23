package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Responses.DTO.TopicWithPercentsDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetTopicInterestResponse extends Response{

    public GetTopicInterestResponse(Map<String, Long> reservationsForTopic, Long totalReservations){
        entities = new LinkedList();
        for(Map.Entry<String, Long> entry: reservationsForTopic.entrySet()){
            entities.add(new TopicWithPercentsDTO(
                    entry.getKey(),
                    (double)entry.getValue()/(double)totalReservations));
        }
    }

    public List getEntities(){
        return entities;
    }
}
