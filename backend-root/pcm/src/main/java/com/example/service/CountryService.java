package com.example.service;

import com.example.entity.Country;
import com.example.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

//**********************************************************************************************************************
    private final CountryRepository countryRepository;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    public Country get(Long id) {
        return this.countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public List<Country> getList() {
        return this.countryRepository.findAll();
    }
//**********************************************************************************************************************

}
