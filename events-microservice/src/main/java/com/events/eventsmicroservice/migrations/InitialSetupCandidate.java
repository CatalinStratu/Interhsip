package com.events.eventsmicroservice.migrations;

import com.events.eventsmicroservice.candidate.Candidate;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;

@ChangeLog(order = "001")
public class InitialSetupCandidate {
    @ChangeSet(order = "01", author = "events", id = "01-addCandidates")
    public void addCandidates(MongockTemplate mongockTemplate) {
        for (int i = 0; i < 10; i++) {
            Candidate candidate = new Candidate();
            candidate.setCandidateId("" + i);
            candidate.setMessage("Candidate apply successfully");
            mongockTemplate.save(candidate);
        }
    }
}
