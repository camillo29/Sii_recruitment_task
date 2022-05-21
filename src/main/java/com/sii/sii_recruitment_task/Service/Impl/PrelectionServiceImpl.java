package com.sii.sii_recruitment_task.Service.Impl;


import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Repository.PrelectionRepository;
import com.sii.sii_recruitment_task.Service.PrelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrelectionServiceImpl implements PrelectionService {
    @Autowired
    private PrelectionRepository prelectionRepository;

    @Override
    public List<Prelection> getPrelections(){
        return prelectionRepository.findAll();
    }

    @Override
    public List<Prelection> getPrelectionsByHour(Time hour){
        return prelectionRepository.findAll()
                .stream()
                .filter(prelection -> prelection.getStartHour().equals(hour))
                .collect(Collectors.toList());
    }

    @Override
    public Prelection findById(Long id) throws Exception{
        return prelectionRepository.findById(id).orElseThrow(()->new Exception("No such prelection"));
    }

    @Override
    public void validatePrelection(Prelection p) throws Exception{
        if(p == null) return;
        if(p.getUsers().size()>=5){
            throw new Exception("Not enough place in this prelection");
        }
    }}
