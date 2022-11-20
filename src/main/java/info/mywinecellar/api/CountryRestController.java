/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.exception.ApiException;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Country;
import info.mywinecellar.service.CountryService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/country")
public class CountryRestController {

    private final CountryService countryService;

    /**
     * Edit a country
     *
     * @param countryId The id of the country to edit
     * @param request   Description and weblink are the only fields that can be edited:
     *                  {@link CountryDto}
     *                  {@link info.mywinecellar.converter.CountryConverter}
     * @return MyWineCellar JSON envelope and the country
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{countryId}/edit")
    public MyWineCellar countryEditPut(@PathVariable Long countryId, @RequestBody(required = false) CountryDto request) {
        if (request != null) {
            Country edit = countryService.editCountry(request, countryId);
            return new Builder().country(edit).build();
        } else {
            log.debug("country request was null for id {}", countryId);
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("country request for id %d was null", countryId));
        }
    }

}
