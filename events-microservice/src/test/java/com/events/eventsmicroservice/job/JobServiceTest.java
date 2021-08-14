package com.events.eventsmicroservice.job;

import com.events.eventsmicroservice.company.Company;
import com.events.eventsmicroservice.company.CompanyDTO;
import com.events.eventsmicroservice.company.CompanyRepository;
import com.events.eventsmicroservice.company.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    JobRepository repository;
    @Mock
    ModelMapper mapper;

    JobService service;

    @BeforeEach
    void setUp() {
        repository = mock(JobRepository.class);
        mapper = mock(ModelMapper.class);
        service = new JobService(repository, mapper);
    }

    @Test
    @DisplayName("Create job event")
    public void testCreateCandidate_thenReturn_Candidate(){
        var jobOk = Optional.of(new Job("ID","TEST MESSAGE"));
        when(repository.save(any(Job.class))).thenReturn(jobOk.get());
        Job job = service.addJobEvent(jobOk.get());

        assertThat(job).isEqualTo(jobOk.get());
    }

    @Test
    @DisplayName("Get All Events By job")
    public void testGetAllEventsByJob_thenReturn_ListOfJobDTO(){
        JobDTO dto = mock(JobDTO.class);
        Job job = mock(Job.class);

        List<Job> jobs = Collections.nCopies(3, job);

        when(repository.findAllByJobId(any())).thenReturn(jobs);
        when(mapper.map(job, JobDTO.class)).thenReturn(dto);

        List<JobDTO> jobDTOS = service.getAllEventsByJob(any());

        assertThat(jobs.size()).isEqualTo(jobDTOS.size());
    }

    @Test
    @DisplayName("Get event by job ID")
    public void testGetJobEvent_thenReturn_JobDTO(){

        JobDTO dto = mock(JobDTO.class);
        var jobOk = Optional.of(new Job("ID","TEST MESSAGE"));

        when(repository.findById(any())).thenReturn(jobOk);
        when(mapper.map(jobOk.get(), JobDTO.class)).thenReturn(dto);

        JobDTO jobDTO = service.getJobEvent(any(String.class));

        assertThat(dto).isEqualTo(jobDTO);
    }

    @Test
    @DisplayName("Update job information when job exist")
    public void testUpdateWhenJobExists_thenReturn_OK() {
        // Given
        var jobOk = Optional.of(new Job("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(jobOk);
        // When
        HttpStatus status = service.updateJobEvent("ID", jobOk.get());
        // Then
        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Update job information when job not exist")
    public void testUpdateWhenJobDoesNotExists_thenNOT_FOUND() {
        // Given
        var jobOk = Optional.of(new Job("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        // When
        HttpStatus status = service.updateJobEvent("ID", jobOk.get());
        // Then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
