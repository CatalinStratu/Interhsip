package com.events.eventsmicroservice.migrations;

import com.events.eventsmicroservice.candidate.Candidate;
import com.events.eventsmicroservice.job.Job;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;

@ChangeLog(order = "002")
public class InitialSetupJob {
    @ChangeSet(order = "01", author = "events", id = "01-addJobs")
    public void addJobs(MongockTemplate mongockTemplate) {
        for(int i = 0; i<10;i++){
            Job job = new Job();
            job.setJobId(""+i);
            job.setMessage("Job is updated successfully");
            mongockTemplate.save(job);
        }
    }
}
