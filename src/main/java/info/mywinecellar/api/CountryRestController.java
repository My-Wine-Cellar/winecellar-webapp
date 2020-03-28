/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.model.Country;
import info.mywinecellar.service.CountryService;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryRestController extends AbstractRestController {

    @Inject
    CountryService countryService;

    /**
     * PUT mapping to update a Country
     *
     * @param request   Country request
     * @param countryId Long countryId
     * @return ResponseEntity.ACCEPTED
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{countryId}/edit")
    public ResponseEntity<?> countryEditPut(@RequestBody CountryDto request, @PathVariable Long countryId) {
        Country edit = countryService.findById(countryId);
        checkObjectNull(edit);
        edit = countryService.editCountry(request, countryId);
        return ResponseEntity.accepted().body("Updated " + edit.toString());
    }

}
