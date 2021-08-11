package com.example.companiesjobscandidatesmicroservice.features.job;

import com.example.companiesjobscandidatesmicroservice.features.job.type.Type;
import com.example.companiesjobscandidatesmicroservice.features.utils.Audit;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "jobs")
public class Job extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "type")
    private Type type;

    @Column(name = "salary", nullable = false)
    private String salary;

    @Column(name = "location")
    private String location;

    @Column( name = "slug")
    private  String slug;

}
