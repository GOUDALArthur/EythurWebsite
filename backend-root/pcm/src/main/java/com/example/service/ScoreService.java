package com.example.service;

import com.example.dto.CumulativeScoreDTO;
import com.example.entity.Cyclist;
import com.example.entity.Race;
import com.example.entity.Score;
import com.example.entity.embedded.ScoreId;
import com.example.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

//**********************************************************************************************************************
    private final ScoreRepository scoreRepository;

    private final CyclistService cyclistService;

    private final RaceService raceService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public ScoreService(ScoreRepository scoreRepository,
                        CyclistService cyclistService,
                        RaceService raceService) {
        this.scoreRepository = scoreRepository;
        this.cyclistService = cyclistService;
        this.raceService = raceService;

    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public Score get(ScoreId id) {
        return this.scoreRepository.findById(id).orElseThrow(() -> new RuntimeException("Score not found"));
    }

    public List<Score> getList() {
        return this.scoreRepository.findAll();
    }

    public List<Score> getListCyclistScore(Long cyclistId) {
        return this.scoreRepository.findByCyclistId(cyclistId);
    }

    public List<Score> getListRaceScore(Long raceId) {
        return this.scoreRepository.findByRaceId(raceId);
    }

    public List<Score> getListTeamScore(Long teamId) {
        return this.scoreRepository.findByTeamId(teamId);
    }

    public List<Score> getListCountryScore(Long countryId) {
        return this.scoreRepository.findByCountryId(countryId);
    }

    public Score add(Long cyclistId, Long raceId, Integer points) {
        // Retrieve foreign key entities using their IDs
        Cyclist cyclist = this.cyclistService.get(cyclistId);
        Race race = this.raceService.get(raceId);

        // Create a new Score entity and set its properties
        Score newScore = new Score();
        newScore.setId(new ScoreId(cyclistId, raceId));
        newScore.setCyclist(cyclist);
        newScore.setRace(race);
        newScore.setPoints(points);

        // Save the new Score entity to the database
        return this.scoreRepository.save(newScore);
    }

    public void delete(Long cyclistId, Long raceId) {
        // Create a ScoreId object using the provided cyclistId and raceId
        ScoreId id = new ScoreId(cyclistId, raceId);

        // Check if the Score entity exists in the database
        if (!this.scoreRepository.existsById(id)) {
            throw new RuntimeException("Score not found");
        }

        // Delete the Score entity with the specified ScoreId from the database
        this.scoreRepository.deleteById(id);
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public List<CumulativeScoreDTO> getListCyclistCumulativeScore(Long raceIdMax) {
        return this.scoreRepository.findCumulativePointsByCyclist(raceIdMax);
    }

    public List<CumulativeScoreDTO> getListTeamCumulativeScore(Long raceIdMax) {
        return this.scoreRepository.findCumulativePointsByTeam(raceIdMax);
    }

    public List<CumulativeScoreDTO> getListCountryCumulativeScore(Long raceIdMax) {
        return this.scoreRepository.findCumulativePointsByCountry(raceIdMax);
    }
//**********************************************************************************************************************
}
