/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/country")
public class CountryController extends AbstractController {

    public CountryController() {
        super();
    }

    @GetMapping("/{countryId}/edit")
    public String countryEditGet(@PathVariable Long countryId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.COUNTRY, countryService.findById(countryId));
        return Paths.COUNTRY_EDIT;
    }

    @PostMapping("/{countryId}/edit")
    public String countryEditPost(@Valid Country country, BindingResult result, Model model,
                                  @PathVariable Long countryId, Principal principal,
                                  @RequestParam("action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            model.addAttribute(Attributes.COUNTRY, country);
            return Paths.COUNTRY_EDIT;
        } else {
            if (action.equals("save")) {
                country.setId(countryId);
                countryService.save(country);
                return redirectCountry(country);
            } else {
                return redirectCountry(country);
            }
        }
    }

}
