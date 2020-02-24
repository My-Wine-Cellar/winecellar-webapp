/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.service.CountryRestService;
import info.mywinecellar.model.Country;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryRestController extends AbstractRestController {

    @Inject CountryRestService restService;

    /**
     * PUT mapping to update a Country
     *
     * @param request   Country request
     * @param countryId Long countryId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/{countryId}/edit")
    public ResponseEntity<?> countryEditPut(@RequestBody Country request, @PathVariable Long countryId) {
        Country update = countryService.findById(countryId);
        checkObjectNull(update);
        restService.updateCountry(update, request);
        return ResponseEntity.accepted().body("Updated " + update.toString());
    }

}
