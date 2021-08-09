package com.events.eventsmicroservice.candidate;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {
    List<CandidateDTO> findAllByCandidateId(String id);
}
