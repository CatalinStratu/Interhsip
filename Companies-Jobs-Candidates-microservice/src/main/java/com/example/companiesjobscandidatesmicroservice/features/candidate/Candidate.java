package com.example.companiesjobscandidatesmicroservice.features.candidate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
