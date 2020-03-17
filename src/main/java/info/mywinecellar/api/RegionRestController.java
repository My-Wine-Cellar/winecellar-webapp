/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Region;
import info.mywinecellar.service.RegionService;
import info.mywinecellar.ui.RegionUI;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> regionEditPut(@RequestBody RegionUI request, @PathVariable Long regionId) {
        Region edit = regionService.findById(regionId);
        checkObjectNull(edit);
        edit = regionService.editRegion(request, regionId);
        return ResponseEntity.accepted().body("Updated " + edit.toString());
    }
}
