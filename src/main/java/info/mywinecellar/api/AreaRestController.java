/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Area;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/area/{areaId}")
public class AreaRestController extends AbstractRestController {

    /**
     * PUT mapping to update an Area
     *
     * @param request AreaDto request
     * @param areaId  Long areaId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/edit")
    public Area areaEditPut(@RequestBody AreaDto request, @PathVariable Long areaId) {
        checkObjectNull(request);
        return areaService.editArea(request, areaId);
    }

    /**
     * PUT mapping to add a Producer to Area
     *
     * @param request ProducerDto request
     * @param areaId  Long areaId
     * @return ResponseEntity.CREATED
     */
    @PostMapping("/addProducer")
    public Area areaAddProducerPost(@RequestBody ProducerDto request, @PathVariable Long areaId) {
        checkObjectNull(request);
        return areaService.addProducer(areaId, request);
    }

    // TODO add endpoint for addGrape
}
