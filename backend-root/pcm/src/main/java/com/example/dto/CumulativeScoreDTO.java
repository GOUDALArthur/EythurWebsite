package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CumulativeScoreDTO {
    private Long instanceId;
    private Integer totalPoints;
    private Integer raceCount;

    public CumulativeScoreDTO(Long instanceId, Long totalPoints, Long raceCount) {
        this.instanceId = instanceId;
        this.totalPoints = totalPoints != null ? totalPoints.intValue() : 0;
        this.raceCount = raceCount != null ? raceCount.intValue() : 0;
    }

    public CumulativeScoreDTO() {}
}
