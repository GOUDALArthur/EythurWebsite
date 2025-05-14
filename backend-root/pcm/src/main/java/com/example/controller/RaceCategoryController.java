package com.example.controller;

import com.example.entity.RaceCategory;
import com.example.service.RaceCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/race-categories")
public class RaceCategoryController {

//**********************************************************************************************************************
    private final RaceCategoryService raceCategoryService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public RaceCategoryController(RaceCategoryService raceCategoryService) {
        this.raceCategoryService = raceCategoryService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<RaceCategory> getList() {
        return this.raceCategoryService.getList();
    }

    @GetMapping("/{id}")
    public RaceCategory get(@PathVariable String id) {
        return this.raceCategoryService.get(id);
    }
//**********************************************************************************************************************

}
