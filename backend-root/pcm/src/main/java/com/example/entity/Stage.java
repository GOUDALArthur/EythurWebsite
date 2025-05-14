package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Stage {

//**********************************************************************************************************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String start;

    @Column(nullable = false)
    private String finish;

    @Column(nullable = false)
    private float distance;

    @Column(nullable = false)
    private Date date;
//**********************************************************************************************************************

//**********************************************************************************************************************
    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "type_code", nullable = false)
    private StageType stageType;
//**********************************************************************************************************************
}
