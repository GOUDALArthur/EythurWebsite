package com.example.entity;

import com.example.entity.embedded.ScoreId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "scores")
public class Score {

//**********************************************************************************************************************
    @EmbeddedId
    private ScoreId id;

    @JoinColumn(nullable = false)
    private int points;
//**********************************************************************************************************************

//**********************************************************************************************************************
    @ManyToOne
    @MapsId("raceId")
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id", nullable = false)
    private Cyclist cyclist;
//**********************************************************************************************************************

}
