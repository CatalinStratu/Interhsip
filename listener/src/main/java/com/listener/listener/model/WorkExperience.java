package com.listener.listener.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = WorkExperience.class)
public class WorkExperience {
    @JsonProperty("Experience")
    public String experience;
    public String date_start;
    public String jobtitle;
    public String text;
    public String date_end;
}
