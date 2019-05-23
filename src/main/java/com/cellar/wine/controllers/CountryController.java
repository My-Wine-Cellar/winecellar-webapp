package com.cellar.wine.controllers;

import com.cellar.wine.services.RegionService;
import com.cellar.wine.services.impl.CountryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/country")
public class CountryController {

    private CountryServiceImpl countryService;
    private RegionService regionService;

    public CountryController(CountryServiceImpl countryService, RegionService regionService) {
        this.countryService = countryService;
        this.regionService = regionService;
    }

    @RequestMapping("/list")
    public String getAllCountriesWithRegions(Model model) {
        model.addAttribute("countries", countryService.findWithRegions());
        return "country/countryList";
    }
}
