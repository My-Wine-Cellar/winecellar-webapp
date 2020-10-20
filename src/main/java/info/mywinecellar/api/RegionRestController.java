/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Region;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
public class RegionRestController extends AbstractRestController {

    /**
     * Edit a region
     *
     * @param request  Description and weblink are the only fields that can be edited:
     *                 {@link RegionDto}
     *                 {@link info.mywinecellar.converter.RegionConverter}
     * @param regionId The id of the region
     * @return MyWineCellar JSON envelope and the region
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{regionId}/edit")
    public MyWineCellar regionEditPut(@RequestBody RegionDto request, @PathVariable Long regionId) {
        checkObjectNull(request);
        Region region = regionService.editRegion(request, regionId);
        log.info("Updated {} {} ", region.toString(), region.getName());

        Builder builder = new Builder();
        builder.region(region);
        return builder.build();
    }
}
