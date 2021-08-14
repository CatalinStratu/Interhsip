package com.events.eventsmicroservice.job;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("jobs")
public class Job {

    @Id
    private String id;

    private String jobId;

    private String message;

    @CreatedDate
    private LocalDateTime dateCreated;

    public Job(String jobId, String message){
        this.jobId = jobId;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
    }

    public String getId(){
        return id;
    }

    public String getJobId(){
        return jobId;
    }

    public String getMessage(){
        return message;
    }

    public void setJobId(String id){
        this.jobId = id;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
