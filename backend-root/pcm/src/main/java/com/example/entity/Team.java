package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {

//**********************************************************************************************************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
//**********************************************************************************************************************

//**********************************************************************************************************************
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
//**********************************************************************************************************************

}
