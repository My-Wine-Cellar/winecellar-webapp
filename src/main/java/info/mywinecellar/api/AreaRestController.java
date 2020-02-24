/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.service.AreaRestService;
import info.mywinecellar.model.Area;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/area")
public class AreaRestController extends AbstractRestController {

    @Inject AreaRestService restService;

    /**
     * PUT mapping to update an Area
     *
     * @param area   Area area
     * @param areaId Long areaId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/{areaId}/edit")
    public ResponseEntity<?> areaEditPut(@RequestBody Area area, @PathVariable Long areaId) {
        Area updateArea = areaService.findById(areaId);
        checkObjectNull(updateArea);
        restService.updateArea(updateArea, area);
        return ResponseEntity.accepted().body("Updated " + updateArea.toString());
    }
}
