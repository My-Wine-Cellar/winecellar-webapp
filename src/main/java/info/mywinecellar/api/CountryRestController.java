/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Country;
import info.mywinecellar.service.CountryService;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/country")
public class CountryRestController {

    @Inject
    CountryService countryService;

    /**
     * Edit a country
     *
     * @param request   Description and weblink are the only fields that can be edited:
     *                  {@link CountryDto}
     *                  {@link info.mywinecellar.converter.CountryConverter}
     * @param countryId The id of the country to edit
     * @return MyWineCellar JSON envelope and the country
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{countryId}/edit")
    public MyWineCellar countryEditPut(@RequestBody CountryDto request, @PathVariable Long countryId) {
        Country country = countryService.editCountry(request, countryId);
        log.info("Updated {} {} ", country.toString(), country.getName());
        return new Builder().country(country).build();
    }

}
