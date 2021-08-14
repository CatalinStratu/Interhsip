package com.events.eventsmicroservice.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Candidate> addCandidateEvent(Candidate candidate){
        return ResponseEntity.ok(candidateService.addCandidateEvent(candidate));
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<HttpStatus> updateCandidateEvent(@PathVariable String id, Candidate candidate){
        return ResponseEntity.ok(candidateService.updateCandidateEvent(id, candidate));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CandidateDTO> getCandidateEvent(@PathVariable String id) {
        return ResponseEntity.ok(candidateService.getCandidateEvent(id));
    }

    @GetMapping(path = "events/{candidateId}")
    public ResponseEntity<List<CandidateDTO>> getAllEventsByCompany(@PathVariable String candidateId){
        return ResponseEntity.ok(candidateService.getAllEventsByCandidate(candidateId));
    }
}
