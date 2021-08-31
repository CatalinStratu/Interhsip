package com.example.aplicationsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AplicationsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AplicationsMicroserviceApplication.class, args);
    }

}
