/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api.service;

import info.mywinecellar.model.Country;
import info.mywinecellar.service.CountryService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CountryRestService {

    @Inject CountryService countryService;

    /**
     * Update a Country from the request
     *
     * @param update  Country update
     * @param request Country request
     */
    public void updateCountry(Country update, Country request) {
        if (request.getDescription() == null) {
            update.setDescription(update.getDescription());
        } else {
            update.setDescription(request.getDescription());
        }
        if (request.getWeblink() == null) {
            update.setWeblink(update.getWeblink());
        } else {
            update.setWeblink(request.getWeblink());
        }
        countryService.save(update);
        log.info("==== Updated {} ====", update.toString());
    }
}
