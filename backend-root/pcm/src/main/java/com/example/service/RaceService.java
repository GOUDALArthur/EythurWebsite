package com.example.service;

import com.example.entity.Race;
import com.example.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

//**********************************************************************************************************************
    private final RaceRepository raceRepository;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public RaceService(RaceRepository raceRepository,
                       RaceCategoryService raceCategoryService,
                       CountryService countryService) {
        this.raceRepository = raceRepository;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public Race get(Long id) {
        return this.raceRepository.findById(id).orElseThrow(() -> new RuntimeException("Race not found"));
    }

    public List<Race> getList() {
        return this.raceRepository.findAll();
    }
//**********************************************************************************************************************

}
