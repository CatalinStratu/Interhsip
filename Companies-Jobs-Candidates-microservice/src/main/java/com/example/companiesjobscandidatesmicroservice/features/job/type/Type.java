package com.example.companiesjobscandidatesmicroservice.features.job.type;

import com.example.companiesjobscandidatesmicroservice.features.utils.Audit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "jobType")
public class Type extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

}
