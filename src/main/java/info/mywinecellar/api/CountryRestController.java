/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

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
     * PUT mapping
     * <p>
     * Updates a country's weblink and/or description
     *
     * @param country   country
     * @param countryId countryId
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{countryId}/edit")
    public void countryEditPut(@RequestBody Country country, @PathVariable Long countryId) {
        Country updateCountry = countryService.findById(countryId);
        checkObjectNull(updateCountry);
        updateCountry.setDescription(country.getDescription());
        updateCountry.setWeblink(country.getWeblink());
        log.info("==== Updated Country -> " + updateCountry.getName() + " ====");
        countryService.save(updateCountry);
    }

}
