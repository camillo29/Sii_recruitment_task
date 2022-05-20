package com.sii.sii_recruitment_task.Responses;


import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


public abstract class Response {
    protected List entities;
    protected String message;
}
