package com.events.eventsmicroservice.candidate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("candidates")
public class Candidate {
    @Id
    private String id;

    private String candidateId;

    private String message;

    @CreatedDate
    private LocalDateTime dateCreated;

    public Candidate(String candidateId, String message){
        this.candidateId = candidateId;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
    }

    public String getId(){
        return id;
    }

    public String getCandidateId(){
        return candidateId;
    }

    public String getMessage(){
        return message;
    }

    public void setCandidateId(String id){
        this.candidateId = id;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
