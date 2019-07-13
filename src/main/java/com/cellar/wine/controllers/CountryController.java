package com.cellar.wine.controllers;

import com.cellar.wine.models.Country;
import com.cellar.wine.services.impl.CountryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

    @GetMapping("/{countryId}/edit")
    public String initEditCountryForm(@PathVariable Long countryId, Model model) {
        model.addAttribute("country", countryService.findById(countryId));
        return "country/editCountry";
    }

    @PostMapping("/{countryId}/edit")
    public String processEditCountryForm(@Valid Country country, BindingResult result, @PathVariable Long countryId) {
        if(result.hasErrors()) {
            return "country/editCountry";
        } else {
            country.setId(countryId);
            Country savedCountry = countryService.save(country);
            return "redirect:/country/" + savedCountry.getId();
        }
    }
}
