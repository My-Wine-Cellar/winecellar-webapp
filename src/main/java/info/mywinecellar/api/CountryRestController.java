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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryRestController extends AbstractRestController {

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
        checkObjectNull(request);
        Country country = countryService.editCountry(request, countryId);
        log.info("Updated {} {} ", country.toString(), country.getName());

        Builder builder = new Builder();
        builder.country(country);
        return builder.build();
    }

}
