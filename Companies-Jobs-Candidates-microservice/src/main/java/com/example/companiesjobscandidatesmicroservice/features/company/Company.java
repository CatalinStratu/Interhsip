package com.example.companiesjobscandidatesmicroservice.features.company;

import com.example.companiesjobscandidatesmicroservice.features.utils.Audit;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "companies")
public class Company extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
