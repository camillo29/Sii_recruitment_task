package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Responses.DTO.OnlyHourNamePrelection;

import java.util.LinkedList;
import java.util.List;

public class GetPrelectionsResponse extends Response{

    public GetPrelectionsResponse(List<Prelection> prelections){
        entities = new LinkedList();
        for(Prelection p: prelections){
            entities.add(new OnlyHourNamePrelection(p.getStartHour(), p.getName()));
        }
    }
    public List getEntities(){
        return entities;
    }
}
