package com.listener.listener.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Basics.class)
public class Basics {
    public String gender;
    public Name name;
    public String title;
    public List<String> email;
    public List<String> url;
}
