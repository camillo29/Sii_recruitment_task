package com.sii.sii_recruitment_task.Service.Impl;


import com.sii.sii_recruitment_task.Model.Prelection;
import com.sii.sii_recruitment_task.Repository.PrelectionRepository;
import com.sii.sii_recruitment_task.Responses.Response;
import com.sii.sii_recruitment_task.Service.PrelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.util.List;

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
        return prelectionRepository.findAllByStartHour(hour);
    }

    @Override
    public List<Prelection> getPrelectionsByTopic(String topic){
        return prelectionRepository.findAllByTopic(topic);
    }

    @Override
    public Prelection findById(Long id) throws ResponseStatusException{
        return prelectionRepository.findById(id)
                .orElseThrow(
                        ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No such prelection"));
    }

    @Override
    public void validatePrelection(Prelection p) throws ResponseStatusException{
        if(p == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such prelection!");
        if(p.getUsers().size()>=5){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough place in this prelection!");
        }
    }}
