package com.sii.sii_recruitment_task.Responses;

import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Responses.DTO.OnlyHourNamePrelection;

import java.util.LinkedList;
import java.util.List;

public class GetPrelectionsResponse extends Response{

    public GetPrelectionsResponse(List<Prelection> prelections){
        entities = new LinkedList();
        for(Prelection p: prelections){
            OnlyHourNamePrelection prelection =
                    new OnlyHourNamePrelection(p.getStartHour(), p.getName());
            entities.add(prelection);
        }
    }
    public List getEntities(){
        return entities;
    }
}
