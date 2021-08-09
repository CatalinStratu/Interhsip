package com.events.eventsmicroservice.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO implements Serializable {

    private String id;
    private String candidateId;
    private String message;
    private LocalDateTime dateCreated;
}
