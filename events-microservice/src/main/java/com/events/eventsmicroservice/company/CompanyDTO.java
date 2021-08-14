package com.events.eventsmicroservice.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {

    private String id;
    private String companyId;
    private String message;
    private LocalDateTime dateCreated;
}
