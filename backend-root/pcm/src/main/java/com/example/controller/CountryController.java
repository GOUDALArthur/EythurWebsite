package com.example.controller;

import com.example.entity.Country;
import com.example.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pcm/countries")
public class CountryController {

//**********************************************************************************************************************
    private final CountryService countryService;
//**********************************************************************************************************************

//**********************************************************************************************************************
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
//**********************************************************************************************************************

//**********************************************************************************************************************
    @GetMapping
    public List<Country> getList() {
        return this.countryService.getList();
    }

    @GetMapping("/{id}")
    public Country get(@PathVariable Long id) {
        return this.countryService.get(id);
    }
//**********************************************************************************************************************

}
