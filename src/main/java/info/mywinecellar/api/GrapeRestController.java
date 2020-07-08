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
     * Red grapes
     *
     * @return MyWineCellar JSON envelope and the list of grapes
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
     * White grapes
     *
     * @return MyWineCellar JSON envelope and the list of grapes
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
     * Details of a grape
     *
     * @param grape Name of the grape
     * @return MyWineCellar JSON envelope and the grape
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
     * Edit a grape
     *
     * @param request Description and weblink are the only fields that can be edited:
     *                {@link GrapeDto}
     *                {@link info.mywinecellar.converter.GrapeConverter}
     * @param grapeId The id of the grape to edit
     * @return MyWineCellar JSON envelope and the grape
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{grapeId}/edit")
    public MyWineCellar grapeEditPut(@RequestBody GrapeDto request, @PathVariable Long grapeId) {
        checkObjectNull(request);
        Grape grape = grapeService.editGrape(request, grapeId);
        log.info("Updated {} {} ", grape.toString(), grape.getName());

        Builder builder = new Builder();
        builder.grape(grape);
        return builder.build();
    }
}
