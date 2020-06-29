/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.AbstractKeyDto;
import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Grape;
import info.mywinecellar.service.GrapeService;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Inject
    GrapeService grapeService;

    /**
     * GET mapping
     *
     * @return red grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/red")
    public MyWineCellar grapeRedGet() {
        Set<Grape> redGrapes = grapeService.getRedGrapes();
        Builder builder = new Builder();
        redGrapes.forEach(builder::grape);
        return builder.build();
    }

    /**
     * GET mapping
     *
     * @return white grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/white")
    public MyWineCellar grapeWhiteGet() {
        Set<Grape> whiteGrapes = grapeService.getWhiteGrapes();
        Builder builder = new Builder();
        whiteGrapes.forEach(builder::grape);
        return builder.build();
    }

    /**
     * GET mapping
     *
     * @param grape String grape
     * @return The grape
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{grape}")
    public MyWineCellar grapeDetailsGet(@PathVariable String grape) {
        Grape grapeDetails = grapeService.findByLowerCaseName(AbstractKeyDto.fromKey(grape));
        Builder builder = new Builder();
        builder.grape(grapeDetails);
        return builder.build();
    }

    /**
     * PUT mapping to update a Grape
     *
     * @param request Grape request
     * @param grapeId Long grapeId
     * @return ResponseEntity.ACCEPTED
     */
    @PutMapping("/{grapeId}/edit")
    public ResponseEntity<?> grapeEditPut(@RequestBody GrapeDto request, @PathVariable Long grapeId) {
        checkObjectNull(request);
        grapeService.editGrape(request, grapeId);
        return ResponseEntity.accepted().body("Updated Grape: " + grapeId);
    }
}
