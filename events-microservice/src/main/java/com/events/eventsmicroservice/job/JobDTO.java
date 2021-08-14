package com.events.eventsmicroservice.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO  implements Serializable {

    private String id;
    private String jobId;
    private String message;
    private LocalDateTime dateCreated;
}
