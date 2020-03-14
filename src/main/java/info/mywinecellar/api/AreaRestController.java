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
import info.mywinecellar.model.Producer;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/area/{areaId}")
public class AreaRestController extends AbstractRestController {

    @Inject AreaRestService restService;

    /**
     * PUT mapping to update an Area
     *
     * @param area   Area area
     * @param areaId Long areaId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/edit")
    public ResponseEntity<?> areaEditPut(@RequestBody Area area, @PathVariable Long areaId) {
        Area updateArea = areaService.findById(areaId);
        checkObjectNull(updateArea);
        restService.updateArea(updateArea, area);
        return ResponseEntity.accepted().body("Updated " + updateArea.toString());
    }

    /**
     * PUT mapping to add a Producer to Area
     *
     * @param producer Producer producer
     * @param areaId   Long areaId
     * @return ResponseEntity.CREATED
     */
    @PostMapping("/addProducer")
    public ResponseEntity<?> areaAddProducerPost(@RequestBody Producer producer, @PathVariable Long areaId) {
        Area area = areaService.findById(areaId);
        area.getProducers().add(producer);
        producerService.save(producer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added " + producer.toString() +
                " to " + area.toString());
    }

    // TODO add endpoint for addGrape
}
