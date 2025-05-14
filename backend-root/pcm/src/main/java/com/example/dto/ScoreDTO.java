package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {
    private Long cyclistId;
    private Long raceId;
    private Integer points;
}