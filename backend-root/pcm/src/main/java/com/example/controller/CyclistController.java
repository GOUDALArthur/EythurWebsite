package com.example.controller;

import com.example.entity.Cyclist;
import com.example.service.CyclistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/cyclists")
public class CyclistController {

//**********************************************************************************************************************
    private final CyclistService cyclistService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public CyclistController(CyclistService cyclistService) {
        this.cyclistService = cyclistService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<Cyclist> getList() {
        return this.cyclistService.getList();
    }

    @GetMapping("/{id}")
    public Cyclist get(@PathVariable Long id) {
        return this.cyclistService.get(id);
    }
//**********************************************************************************************************************

}
