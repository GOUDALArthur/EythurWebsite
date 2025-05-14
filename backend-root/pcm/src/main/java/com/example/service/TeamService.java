package com.example.service;

import com.example.entity.Team;
import com.example.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

//**********************************************************************************************************************
    private final TeamRepository teamRepository;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public Team get(Long id) {
        return this.teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public List<Team> getList() {
        return this.teamRepository.findAll();
    }
//**********************************************************************************************************************

}
