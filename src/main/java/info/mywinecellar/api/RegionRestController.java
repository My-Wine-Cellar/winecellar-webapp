/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

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
     * PUT mapping
     *
     * @param region   region
     * @param regionId regionId
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{regionId}/edit")
    public void regionEditPut(@RequestBody Region region, @PathVariable Long regionId) {
        Region updateRegion = regionService.findById(regionId);
        checkObjectNull(updateRegion);
        updateRegion.setDescription(region.getDescription());
        updateRegion.setWeblink(region.getWeblink());
        log.info("==== Updating region -> " + updateRegion.getName() + " ====");
        regionService.save(updateRegion);
    }
}
