package com.events.eventsmicroservice.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Job> addJobEvent(Job job){
        return ResponseEntity.ok(jobService.addJobEvent(job));
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<HttpStatus> updateJobEvent(@PathVariable String id, Job job){
        return ResponseEntity.ok(jobService.updateJobEvent(id, job));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<JobDTO>> getJobEvent(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getJobEvent(id));
    }

    @GetMapping(path = "events/{jobId}")
    public ResponseEntity<List<JobDTO>> getAllEventsByJob(@PathVariable String jobId){
        return ResponseEntity.ok(jobService.getAllEventsByJob(jobId));
    }
}
