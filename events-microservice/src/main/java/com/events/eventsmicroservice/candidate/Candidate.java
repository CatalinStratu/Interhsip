package com.events.eventsmicroservice.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("candidates")
public class Candidate {
    @Id
    private String id;
    @Field("candidateId")
    private String candidateId;
    @Field("message")
    private String message;

    @CreatedDate
    private LocalDateTime dateCreated;

    public Candidate(String candidateId, String message){
        this.candidateId = candidateId;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
    }
}
