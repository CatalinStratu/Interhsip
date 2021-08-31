package com.listener.listener.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Data.class)
public class Data {
    public List<Summary> summary;
    public List<EducationAndTraining> education_and_training;
    public List<Skill> skills;
    public Basics basics;
    public List<Award> awards;
    public List<WorkExperience> work_experience;
}
