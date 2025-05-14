package com.example.controller;

import com.example.entity.Team;
import com.example.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/teams")
public class TeamController {

//**********************************************************************************************************************
    private final TeamService teamService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<Team> getList() {
        return this.teamService.getList();
    }

    @GetMapping("/{id}")
    public Team get(@PathVariable Long id) {
        return this.teamService.get(id);
    }
//**********************************************************************************************************************

}
