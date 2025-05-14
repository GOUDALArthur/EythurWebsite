package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Race {

//**********************************************************************************************************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
//**********************************************************************************************************************

//**********************************************************************************************************************
    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "category_code", nullable = false)
    private RaceCategory raceCategory;
//**********************************************************************************************************************

}
