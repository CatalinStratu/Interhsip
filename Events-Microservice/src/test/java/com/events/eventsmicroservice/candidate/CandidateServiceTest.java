package com.events.eventsmicroservice.candidate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Optional;

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
    public void whenCandidateExists_thenReturn_OK() {
        // given
        var candidateOk = Optional.of(new Candidate("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(candidateOk);
        // when
        HttpStatus status = service.updateCandidateEvent("ID", candidateOk.get());
        // then
        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void whenCandidateDoesNotExists_thenNOT_FOUND() {
        // given
        var candidateOk = Optional.of(new Candidate("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        // when
        HttpStatus status = service.updateCandidateEvent("ID", candidateOk.get());
        // then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
