package com.example.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ScoreId implements Serializable {

    private Long cyclistId;
    private Long raceId;

    // Default constructor is required by JPA, even if not used
    public ScoreId() {}

    public ScoreId(Long cyclistId, Long raceId) {
        this.cyclistId = cyclistId;
        this.raceId = raceId;
    }

}
