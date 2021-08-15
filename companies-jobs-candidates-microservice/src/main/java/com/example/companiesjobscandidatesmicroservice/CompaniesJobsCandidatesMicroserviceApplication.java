package com.example.companiesjobscandidatesmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class CompaniesJobsCandidatesMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompaniesJobsCandidatesMicroserviceApplication.class, args);
    }

}
