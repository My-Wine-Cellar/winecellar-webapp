/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Region;
import info.mywinecellar.service.RegionService;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
public class RegionRestController extends AbstractRestController {

    @Inject
    RegionService regionService;

    /**
     * PUT mapping to update a Region
     *
     * @param request  Region request
     * @param regionId Long regionId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/{regionId}/edit")
    public Region regionEditPut(@RequestBody RegionDto request, @PathVariable Long regionId) {
        checkObjectNull(request);
        return regionService.editRegion(request, regionId);
    }
}
