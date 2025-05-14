package com.example.service;

import com.example.entity.Cyclist;
import com.example.repository.CyclistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CyclistService {

//**********************************************************************************************************************
    private final CyclistRepository cyclistRepository;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public CyclistService(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public Cyclist get(Long id) {
        return this.cyclistRepository.findById(id).orElseThrow(() -> new RuntimeException("Cyclist not found"));
    }

    public List<Cyclist> getList() {
        return this.cyclistRepository.findAll();
    }
//**********************************************************************************************************************

}
