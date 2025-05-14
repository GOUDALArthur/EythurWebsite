package com.example.controller;

import com.example.entity.Race;
import com.example.service.RaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/races")
public class RaceController {

//**********************************************************************************************************************
    private final RaceService raceService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<Race> getList() {
        return this.raceService.getList();
    }

    @GetMapping("/{id}")
    public Race get(@PathVariable Long id) {
        return this.raceService.get(id);
    }
//**********************************************************************************************************************

}
