package com.example.service;

import com.example.entity.Stage;
import com.example.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

//**********************************************************************************************************************
    private final StageRepository stageRepository;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public Stage get(Long id) {
        return this.stageRepository.findById(id).orElseThrow(() -> new RuntimeException("Stage not found"));
    }

    public List<Stage> getList() {
        return this.stageRepository.findAll();
    }

    public List<Stage> getListByRaceId(Long raceId) {
        return this.stageRepository.findByRaceIdOrderByDateAsc(raceId);
    }
//**********************************************************************************************************************

}
