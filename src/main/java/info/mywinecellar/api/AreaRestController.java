/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Area;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/area")
public class AreaRestController extends AbstractRestController {

    /**
     * PUT mapping
     *
     * @param area   area
     * @param areaId areaId
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{areaId}/edit")
    public void areaEditPut(@RequestBody Area area, @PathVariable Long areaId) {
        Area updateArea = areaService.findById(areaId);
        checkObjectNull(updateArea);
        updateArea.setDescription(area.getDescription());
        updateArea.setWeblink(area.getWeblink());
        log.info("==== Updating area -> " + updateArea.getName() + " ====");
        areaService.save(updateArea);
    }
}
