package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Repository.PrelectionRepository;
import com.sii.sii_recruitment_task.Responses.GetAllPrelectionsResponse;
import com.sii.sii_recruitment_task.Responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prelections")
public class PrelectionController {
    @Autowired
    PrelectionRepository prelectionRepository;

    @GetMapping("/conferencePlan")
    public Response getPrelections(){
        return new GetAllPrelectionsResponse(prelectionRepository.findAll());
    }
}
