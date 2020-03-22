/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Area;
import info.mywinecellar.ui.AreaUI;
import info.mywinecellar.ui.ProducerUI;

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

    /**
     * PUT mapping to update an Area
     *
     * @param areaUI   Area area
     * @param areaId Long areaId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/edit")
    public ResponseEntity<?> areaEditPut(@RequestBody AreaUI areaUI, @PathVariable Long areaId) {
        checkObjectNull(areaUI);
        Area area = areaService.editArea(areaUI, areaId);
        return ResponseEntity.accepted().body("Updated " + area.toString());
    }

    /**
     * PUT mapping to add a Producer to Area
     *
     * @param producer Producer producer
     * @param areaId   Long areaId
     * @return ResponseEntity.CREATED
     */
    @PostMapping("/addProducer")
    public ResponseEntity<?> areaAddProducerPost(@RequestBody ProducerUI producer, @PathVariable Long areaId) {
        checkObjectNull(producer);
        Area area = areaService.areaAddProducer(producer, areaId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added " + producer.getName() +
                " to " + area.toString());
    }

    // TODO add endpoint for addGrape
}
