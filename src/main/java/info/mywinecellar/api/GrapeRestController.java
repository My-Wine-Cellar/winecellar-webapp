/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.exception.ApiException;
import info.mywinecellar.dto.AbstractKeyDto;
import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Grape;
import info.mywinecellar.service.GrapeService;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/grape")
public class GrapeRestController {

    private final GrapeService grapeService;

    /**
     * Red grapes
     *
     * @return MyWineCellar JSON envelope and the list of grapes
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/red")
    public MyWineCellar grapeRedGet() {
        Set<Grape> redGrapes = grapeService.getRedGrapes();
        return new Builder().grapes(redGrapes).build();
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
        return new Builder().grapes(whiteGrapes).build();
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
        Grape entity = grapeService.findByLowerCaseName(AbstractKeyDto.fromKey(grape));
        return new Builder().grape(entity).build();
    }

    /**
     * Edit a grape
     *
     * @param grapeId The id of the grape to edit
     * @param request Description and weblink are the only fields that can be edited:
     *                {@link GrapeDto}
     *                {@link info.mywinecellar.converter.GrapeConverter}
     * @return MyWineCellar JSON envelope and the grape
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{grapeId}/edit")
    public MyWineCellar grapeEditPut(@PathVariable Long grapeId, @RequestBody(required = false) GrapeDto request) {
        if (request != null) {
            Grape grape = grapeService.editGrape(request, grapeId);
            return new Builder().grape(grape).build();
        } else {
            log.debug("grape request was null for id {}", grapeId);
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("grape request for id %d was null", grapeId));
        }
    }
}
