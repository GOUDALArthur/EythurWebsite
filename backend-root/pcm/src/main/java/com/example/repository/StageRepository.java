package com.example.repository;

import com.example.entity.Race;
import com.example.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    List<Stage> findByRaceIdOrderByDateAsc(Long raceId);

}
