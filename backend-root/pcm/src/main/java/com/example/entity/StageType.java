package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "stage_type")
public class StageType {

//**********************************************************************************************************************
    @Id
    private String code;

    @Column(nullable = false)
    private String label;
//**********************************************************************************************************************

}
