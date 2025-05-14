package com.example.controller;

import com.example.entity.Stage;
import com.example.service.StageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/stages")
public class StageController {

//**********************************************************************************************************************
    private final StageService stageService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<Stage> getList() {
        return this.stageService.getList();
    }

    @GetMapping("/{id}")
    public Stage get(@PathVariable Long id) {
        return this.stageService.get(id);
    }

    @GetMapping("races/{raceId}")
    public List<Stage> getListByRaceId(@PathVariable Long raceId) {
        return this.stageService.getListByRaceId(raceId);
    }
//**********************************************************************************************************************

}
