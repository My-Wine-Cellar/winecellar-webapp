/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.model.Country;
import info.mywinecellar.nav.Attributes;
import info.mywinecellar.nav.Paths;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/country")
public class CountryController extends AbstractController {

    /**
     * Default constructor
     */
    public CountryController() {
        super();
    }

    /**
     * @param countryId countryId
     * @param model     model
     * @param principal principal
     * @return View
     */
    @GetMapping("/{countryId}/edit")
    public String countryEditGet(@PathVariable Long countryId, Model model, Principal principal) {
        principalNull(principal);

        model.addAttribute(Attributes.COUNTRY, countryConverter.toDto(countryService.findById(countryId)));
        return Paths.COUNTRY_EDIT;
    }

    /**
     * @param countryDto country
     * @param result     result
     * @param model      model
     * @param countryId  countryId
     * @param principal  principal
     * @param action     action
     * @return View
     */
    @PostMapping("/{countryId}/edit")
    public String countryEditPost(@Valid CountryDto countryDto, BindingResult result, Model model,
                                  @PathVariable Long countryId, Principal principal,
                                  @RequestParam("action") String action) {
        principalNull(principal);

        if (result.hasErrors()) {
            model.addAttribute(Attributes.COUNTRY, countryDto);
            return Paths.COUNTRY_EDIT;
        } else {
            if (action.equals("save")) {
                Country country = countryService.editCountry(countryDto, countryId);
                return redirectCountry(country);
            } else {
                return redirectCountry(countryId);
            }
        }
    }

}
