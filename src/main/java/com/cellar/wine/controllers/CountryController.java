package com.cellar.wine.controllers;

import com.cellar.wine.models.Country;
import com.cellar.wine.services.CountryService;
import com.cellar.wine.ui.CountryUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/country")
public class CountryController {

    private static final String MODEL_ATTRIBUTE_COUNTRY = "country";

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{countryId}/edit")
    public String countryEditGet(@PathVariable Long countryId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        model.addAttribute(MODEL_ATTRIBUTE_COUNTRY, countryService.findById(countryId));
        return "country/editCountry";
    }

    @PostMapping("/{countryId}/edit")
    public String countryEditPost(@Valid Country country, BindingResult result, @PathVariable Long countryId, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }

        if(result.hasErrors()) {
            return "country/editCountry";
        } else {
            country.setId(countryId);
            Country savedCountry = countryService.save(country);
            CountryUI ui = new CountryUI(savedCountry);
            return "redirect:/d/" + ui.getKey();
        }
    }
}
