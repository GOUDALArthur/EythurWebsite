package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cyclist {

//**********************************************************************************************************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
//**********************************************************************************************************************

//**********************************************************************************************************************
    @ManyToOne
    @JoinColumn(nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;
//**********************************************************************************************************************

//**********************************************************************************************************************
    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
//**********************************************************************************************************************

}
