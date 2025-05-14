package com.example.controller;

import com.example.dto.CumulativeScoreDTO;
import com.example.dto.ScoreDTO;
import com.example.entity.Score;
import com.example.entity.embedded.ScoreId;
import com.example.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/scores")
public class ScoreController {

//**********************************************************************************************************************
    private final ScoreService scoreService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<Score> getList() {
        return this.scoreService.getList();
    }

    @GetMapping("/{id}")
    public Score get(@PathVariable ScoreId id) {
        return this.scoreService.get(id);
    }

    @GetMapping("/cyclists/{cyclistId}")
    public List<Score> getListCyclistScore(@PathVariable Long cyclistId) {
        return this.scoreService.getListCyclistScore(cyclistId);
    }

    @GetMapping("/cyclists/cumulative/{raceIdMax}")
    public List<CumulativeScoreDTO> getListCyclistCumulativeScore(@PathVariable Long raceIdMax) {
        return this.scoreService.getListCyclistCumulativeScore(raceIdMax);
    }

    @GetMapping("/races/{raceId}")
    public List<Score> getListRaceScore(@PathVariable Long raceId) {
        return this.scoreService.getListRaceScore(raceId);
    }

    @GetMapping("/teams/cumulative/{raceIdMax}")
    public List<CumulativeScoreDTO> getListTeamCumulativeScore(@PathVariable Long raceIdMax) {
        return this.scoreService.getListTeamCumulativeScore(raceIdMax);
    }

    @GetMapping("/countries/{countryId}")
    public List<Score> getListCountryScore(@PathVariable Long countryId) {
        return this.scoreService.getListCountryScore(countryId);
    }

    @GetMapping("/countries/cumulative/{raceIdMax}")
    public List<CumulativeScoreDTO> getListCountryCumulativeScore(@PathVariable Long raceIdMax) {
        return this.scoreService.getListCountryCumulativeScore(raceIdMax);
    }

    @PostMapping
    public Score create(@RequestBody ScoreDTO scoreDTO) {
        Long cyclistId = scoreDTO.getCyclistId();
        Long raceId = scoreDTO.getRaceId();
        Integer points = scoreDTO.getPoints();
        return this.scoreService.add(cyclistId, raceId, points);
    }

    @DeleteMapping("/{cyclistId}/{raceId}")
    public void delete(@PathVariable Long cyclistId, @PathVariable Long raceId) {
        this.scoreService.delete(cyclistId, raceId);
    }
//**********************************************************************************************************************

}
