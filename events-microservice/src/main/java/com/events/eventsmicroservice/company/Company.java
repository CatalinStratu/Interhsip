package com.events.eventsmicroservice.company;

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
@Document("companies")
public class Company {

    @Id
    private String id;
    @Field("companyId")
    private String companyId;
    @Field("message")
    private String message;

    @CreatedDate
    private LocalDateTime dateCreated;

    public Company(String companyId, String message){
        this.companyId = companyId;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
    }
}