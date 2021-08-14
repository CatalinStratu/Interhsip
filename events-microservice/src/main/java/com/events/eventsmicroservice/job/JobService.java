package com.events.eventsmicroservice.job;

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
public class JobService {


    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public JobService(JobRepository jobRepository, ModelMapper modelMapper) {

        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Add Job Event
     *
     * @param job The job model
     * @return Details of the job event
     */
    public Job addJobEvent(@NotNull Job job) {
        return jobRepository.save(job);
    }

    /**
     * Update job Event
     *
     * @param id event id
     * @param job The job model
     * @return Details of the job event
     */
    public HttpStatus updateJobEvent(@NotNull String id, @NotNull Job job) {
        Optional<Job> jobData = jobRepository.findById(id);

        return jobData.map(result -> {
            Job toBeUpdate = jobData.get();
            toBeUpdate.setJobId(job.getJobId());
            toBeUpdate.setMessage(job.getMessage());
            jobRepository.save(toBeUpdate);
            return HttpStatus.OK;
        }).orElse(HttpStatus.NOT_FOUND);
    }

    /**
     * Return Job event
     *
     * @param id The job model
     * @return Details of the job event
     */
    public JobDTO getJobEvent(@NotNull String id) {
        return jobRepository
                .findById(id)
                .map(result -> modelMapper.map(result,JobDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Return all job events
     *
     * @param id The job model
     * @return Details of the job event
     */
    public List<JobDTO> getAllEventsByJob(@NotNull String id){
        return jobRepository
                .findAllByJobId(id)
                .stream()
                .map(result -> modelMapper.map(result,JobDTO.class))
                .collect(Collectors.toList());
    }
}
