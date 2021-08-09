package com.events.eventsmicroservice.candidate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, ModelMapper modelMapper) {

        this.candidateRepository = candidateRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Add candidate Event
     *
     * @param candidate The candidate model
     * @return Details of the candidate event
     */
    public Candidate addCandidateEvent(@NotNull Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    /**
     * Update candidate Event
     *
     * @param id        candidate event ID
     * @param candidate The candidate model
     * @return Details of the candidate event
     */
    public HttpStatus updateCandidateEvent(@NotNull String id, @NotNull Candidate candidate) {
        Optional<Candidate> candidateData = candidateRepository.findById(id);

        return candidateData.map(result -> {
            Candidate toBeUpdate = candidateData.get();
            toBeUpdate.setCandidateId(candidate.getCandidateId());
            toBeUpdate.setMessage(candidate.getMessage());
            candidateRepository.save(toBeUpdate);
            return HttpStatus.OK;
        }).orElse(HttpStatus.NOT_FOUND);
    }

    /**
     * Return candidate event
     *
     * @param id The candidate model
     * @return Details of the candidate event
     */
    public CandidateDTO getCandidateEvent(@NotNull String id) {
        return candidateRepository
                .findById(id)
                .map(result -> modelMapper.map(result, CandidateDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Return all candidate events
     *
     * @param id The candidate model
     * @return Details of the candidate event
     */
    public List<CandidateDTO> getAllEventsByCandidate(@NotNull String id) {
        return candidateRepository
                .findAllByCandidateId(id)
                .stream()
                .map(result -> modelMapper.map(result, CandidateDTO.class))
                .collect(Collectors.toList());
    }
}
