/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.exception.ApiException;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Region;
import info.mywinecellar.service.RegionService;

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
@RequestMapping("${apiPrefix}/region")
public class RegionRestController {

    private final RegionService regionService;

    /**
     * Edit a region
     *
     * @param regionId The id of the region
     * @param request  Description and weblink are the only fields that can be edited:
     *                 {@link RegionDto}
     *                 {@link info.mywinecellar.converter.RegionConverter}
     * @return MyWineCellar JSON envelope and the region
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{regionId}/edit")
    public MyWineCellar regionEditPut(@PathVariable Long regionId, @RequestBody(required = false) RegionDto request) {
        if (request != null) {
            Region edit = regionService.editRegion(request, regionId);
            return new Builder().region(edit).build();
        } else {
            log.debug("region request was null for id {}", regionId);
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("region request for id %d was null", regionId));
        }
    }
}
