package com.events.eventsmicroservice.migrations;

import com.events.eventsmicroservice.company.Company;
import com.events.eventsmicroservice.job.Job;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;

@ChangeLog(order = "002")
public class InitialSetupCompany {
    @ChangeSet(order = "01", author = "events", id = "01-addCompanies")
    public void addCompanies(MongockTemplate mongockTemplate) {
        for(int i = 0; i<10;i++){
            Company company = new Company();
            company.setCompanyId(""+i);
            company.setMessage("Company is updated successfully");
            mongockTemplate.save(company);
        }
    }
}
