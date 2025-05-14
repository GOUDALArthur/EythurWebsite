package com.example.repository;

import com.example.dto.CumulativeScoreDTO;
import com.example.entity.Score;
import com.example.entity.embedded.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScoreId> {

    List<Score> findByCyclistId(Long cyclistId);

    List<Score> findByRaceId(Long raceId);

    @Query("SELECT s FROM Score s WHERE s.cyclist.team.id = :team_id")
    List<Score> findByTeamId(@Param("team_id") Long teamId);

    @Query("SELECT s FROM Score s WHERE s.cyclist.country.id = :country_id")
    List<Score> findByCountryId(@Param("country_id") Long countryId);

    @Query("SELECT new com.example.dto.CumulativeScoreDTO(s.cyclist.id, SUM(s.points), COUNT(s)) " +
           "FROM Score s " +
           "WHERE s.race.id <= :raceIdMax " +
           "GROUP BY s.cyclist.id " +
           "ORDER BY SUM(s.points) DESC")
    List<CumulativeScoreDTO> findCumulativePointsByCyclist(@Param("raceIdMax") Long raceIdMax);

    @Query("SELECT new com.example.dto.CumulativeScoreDTO(s.cyclist.team.id, SUM(s.points), COUNT(s)) " +
           "FROM Score s " +
           "WHERE s.race.id <= :raceIdMax " +
           "GROUP BY s.cyclist.team.id " +
           "ORDER BY SUM(s.points) DESC")
    List<CumulativeScoreDTO> findCumulativePointsByTeam(@Param("raceIdMax") Long raceIdMax);

    @Query("SELECT new com.example.dto.CumulativeScoreDTO(s.cyclist.country.id, SUM(s.points), COUNT(s)) " +
           "FROM Score s " +
           "WHERE s.race.id <= :raceIdMax " +
           "GROUP BY s.cyclist.country.id " +
           "ORDER BY SUM(s.points) DESC")
    List<CumulativeScoreDTO> findCumulativePointsByCountry(@Param("raceIdMax") Long raceIdMax);
}
