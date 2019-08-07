package com.cellar.wine.controllers;

import com.cellar.wine.models.Country;
import com.cellar.wine.nav.Attributes;
import com.cellar.wine.nav.Paths;
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
public class CountryController extends AbstractController {

    public CountryController() {
    }

    @GetMapping("/{countryId}/edit")
    public String countryEditGet(@PathVariable Long countryId, Model model, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        model.addAttribute(Attributes.COUNTRY, countryService.findById(countryId));
        return Paths.COUNTRY_EDIT;
    }

    @PostMapping("/{countryId}/edit")
    public String countryEditPost(@Valid Country country, BindingResult result, Model model,
                                  @PathVariable Long countryId, Principal principal) {
        if (principal == null) {
            return Paths.REDIRECT_ROOT;
        }

        if (result.hasErrors()) {
            model.addAttribute(Attributes.COUNTRY, country);
            return Paths.COUNTRY_EDIT;
        } else {
            country.setId(countryId);
            countryService.save(country);
            return redirectCountry(country);
        }
    }
}
