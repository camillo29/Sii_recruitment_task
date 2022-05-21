package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Responses.DTO.PrelectionWithPercentsDTO;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetPrelectionsInterestResponse extends Response{

    public GetPrelectionsInterestResponse(List<Prelection> prelections, Map<Time, Long> reservations){
        entities = new LinkedList<>();
        Double percents = 0.0;
        for(Prelection p: prelections){
            Long reserv = reservations.get(p.getStartHour());
            if(reserv != 0){
                percents = ((double)p.getUsers().size()/(double)reserv)*100.0f;
            }
            entities.add(new PrelectionWithPercentsDTO(
                    p.getId(),
                    p.getStartHour(),
                    p.getName(),
                    percents));
        }
    }

    public List getEntities(){
        return entities;
    }
}
