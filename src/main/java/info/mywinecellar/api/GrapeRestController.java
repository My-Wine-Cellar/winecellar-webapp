/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Grape;
import info.mywinecellar.ui.AbstractKeyUI;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grape")
public class GrapeRestController extends AbstractRestController {

    /**
     * GET mapping
     *
     * @return red grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/red")
    public List<Grape> grapeRedGet() {
        List<Grape> redGrapes = grapeService.getRedGrapes();
        checkObjectListNull(redGrapes);
        log.info("==== Show me red grapes ==== ");
        return redGrapes;
    }

    /**
     * GET mapping
     *
     * @return white grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/white")
    public List<Grape> grapeWhiteGet() {
        List<Grape> whiteGrapes = grapeService.getWhiteGrapes();
        checkObjectListNull(whiteGrapes);
        log.info("==== Show me white grapes ====");
        return whiteGrapes;
    }

    /**
     * GET mapping
     *
     * @return all grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public List<Grape> grapeListGet() {
        List<Grape> allGrapes = grapeService.findAll();
        checkObjectListNull(allGrapes);
        log.info("==== Show me all grapes ====");
        return allGrapes;
    }

    /**
     * GET mapping
     *
     * @param grape grape
     * @return grape
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{grape}")
    public Grape grapeDetailsGet(@PathVariable String grape) {
        Grape grapeDetails = grapeService.findByLowerCaseName(AbstractKeyUI.fromKey(grape));
        checkObjectNull(grapeDetails);
        log.info("==== Grape -> " + grapeDetails.getName() + " ====");
        return grapeDetails;
    }

    /**
     * PUT mapping
     *
     * @param grape   grape
     * @param grapeId grapeId
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{grapeId}/edit")
    public void grapeEditPut(@RequestBody Grape grape, @PathVariable Long grapeId) {
        Grape updateGrape = grapeService.findById(grapeId);
        checkObjectNull(updateGrape);
        updateGrape.setDescription(grape.getDescription());
        updateGrape.setWeblink(grape.getWeblink());
        log.info("==== Updated grape -> " + updateGrape.getName() + " ====");
        grapeService.save(updateGrape);
    }
}
