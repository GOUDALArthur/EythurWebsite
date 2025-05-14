package com.example.service;

import com.example.entity.RaceCategory;
import com.example.repository.RaceCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceCategoryService {

//**********************************************************************************************************************
    private final RaceCategoryRepository raceCategoryRepository;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public RaceCategoryService(RaceCategoryRepository raceCategoryRepository) {
        this.raceCategoryRepository = raceCategoryRepository;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public RaceCategory get(String id) {
        return this.raceCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<RaceCategory> getList() {
        return this.raceCategoryRepository.findAll();
    }
//**********************************************************************************************************************

}
