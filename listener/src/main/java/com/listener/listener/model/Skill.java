package com.listener.listener.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Skill.class)
public class Skill {
    @JsonProperty("Technologies")
    public String technologies;
    @JsonProperty("Skills")
    public String skills;
}
