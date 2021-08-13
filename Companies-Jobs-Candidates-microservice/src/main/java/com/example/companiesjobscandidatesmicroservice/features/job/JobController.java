package com.example.companiesjobscandidatesmicroservice.features.job;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @GetMapping("/active/{slug}")
    public String active(){
        return "true";
    }
}
