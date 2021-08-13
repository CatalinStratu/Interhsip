package com.events.eventsmicroservice.candidate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    CandidateRepository repository;

    @Mock
    ModelMapper mapper;

    CandidateService service;

    @BeforeEach
    void setUp() {
        repository = mock(CandidateRepository.class);
        mapper = mock(ModelMapper.class);
        service = new CandidateService(repository, mapper);
    }

    @Test
    @DisplayName("Create candidate event")
    public void testCreateCandidate_thenReturn_Candidate(){
        // Given
        var candidateOk = Optional.of(new Candidate("ID","TEST MESSAGE"));
        when(repository.save(any(Candidate.class))).thenReturn(candidateOk.get());
        // When
        Candidate candidate = service.addCandidateEvent(candidateOk.get());
        // Then
        assertThat(candidate).isEqualTo(candidateOk.get());
    }

    @Test
    @DisplayName("Get All Events By Candidate")
    public void testGetAllEventsByCandidate_thenReturn_ListOfCandidateDTO(){

        CandidateDTO dto = mock(CandidateDTO.class);
        Candidate candidate = mock(Candidate.class);

        List<Candidate> candidates = Collections.nCopies(3, candidate);

        when(repository.findAllByCandidateId(any())).thenReturn(candidates);
        when(mapper.map(candidate, CandidateDTO.class)).thenReturn(dto);

        List<CandidateDTO> candidateDTOS = service.getAllEventsByCandidate(any());

        assertThat(candidates.size()).isEqualTo(candidateDTOS.size());
    }

    @Test
    @DisplayName("Get event by candidate ID")
    public void testGetCandidateEvent_thenReturn_CandidateDTO(){

        CandidateDTO dto = mock(CandidateDTO.class);
        var candidateOk = Optional.of(new Candidate("ID","TEST MESSAGE"));

        when(repository.findById(any())).thenReturn(candidateOk);
        when(mapper.map(candidateOk.get(), CandidateDTO.class)).thenReturn(dto);

        CandidateDTO candidateDTO = service.getCandidateEvent(any(String.class));

        assertThat(dto).isEqualTo(candidateDTO);
    }

    @Test
    @DisplayName("Update candidate information when candidate exist")
    public void testUpdateWhenCandidateExists_thenReturn_OK() {
        // Given
        var candidateOk = Optional.of(new Candidate("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(candidateOk);
        // When
        HttpStatus status = service.updateCandidateEvent("ID", candidateOk.get());
        // Then
        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Update candidate information when candidate not exist")
    public void testUpdateWhenCandidateDoesNotExists_thenNOT_FOUND() {
        // Given
        var candidateOk = Optional.of(new Candidate("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        // When
        HttpStatus status = service.updateCandidateEvent("ID", candidateOk.get());
        // Then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
