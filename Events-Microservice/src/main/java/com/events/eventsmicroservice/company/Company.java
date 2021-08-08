package com.events.eventsmicroservice.company;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("companies")
public class Company {

    @Id
    private String id;

    private String companyId;

    private String message;

    public Company(String companyId, String message){
        this.companyId = companyId;
        this.message = message;
    }

    public String getId(){
        return id;
    }

    public String getCompanyId(){
        return companyId;
    }

    public String getMessage(){
        return message;
    }

    public void setCompanyId(String id){
        this.companyId = id;
    }

    public void setMessage(String message){
        this.message = message;
    }
}