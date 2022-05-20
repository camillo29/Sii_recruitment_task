package com.sii.sii_recruitment_task.Controller;

import com.sii.sii_recruitment_task.Responses.GetPrelectionsResponse;
import com.sii.sii_recruitment_task.Responses.Response;
import com.sii.sii_recruitment_task.Service.PrelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prelections")
public class PrelectionController {
    @Autowired
    private PrelectionService prelectionService;

    @GetMapping("/conferencePlan")
    public Response getPrelections(){
        return new GetPrelectionsResponse(prelectionService.getPrelections());
    }
}
