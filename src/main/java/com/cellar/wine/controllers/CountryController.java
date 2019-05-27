package com.cellar.wine.controllers;

import com.cellar.wine.services.impl.CountryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/country")
public class CountryController {

    private CountryServiceImpl countryService;

    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public String getCountriesWithRegions(Model model) {
        model.addAttribute("countries", countryService.findWithRegions());
        return "country/countryList";
    }

    @GetMapping("/{countryId}")
    public String countryDetails(@PathVariable Long countryId, Model model) {
        model.addAttribute("country", countryService.findById(countryId));
        return "country/countryDetails";
    }
}
