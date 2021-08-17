package com.events.eventsmicroservice.job;

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
@Document("jobs")
public class Job {

    @Id
    private String id;
    @Field("jobId")
    private String jobId;
    @Field("message")
    private String message;

    @CreatedDate
    private LocalDateTime dateCreated;

    public Job(String jobId, String message){
        this.jobId = jobId;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
    }

}
