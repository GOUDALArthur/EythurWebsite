package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "race_category")
public class RaceCategory {

//**********************************************************************************************************************
    @Id
    private String code;

    @Column(nullable = false)
    private String label;
//**********************************************************************************************************************

}
