/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.exception.ApiException;
import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Grape;
import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.GrapeService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/area/{areaId}")
public class AreaRestController {

    private final AreaService areaService;
    private final GrapeService grapeService;

    /**
     * Details of an area
     *
     * @param areaId The id of the area
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public MyWineCellar areaByIdGet(@PathVariable Long areaId) {
        Area area = areaService.findById(areaId);
        return new Builder().area(area).build();
    }

    /**
     * Edit an area
     *
     * @param areaId  The id of the area to edit
     * @param request Description and weblink are the only fields that can be edited:
     *                {@link AreaDto}
     *                {@link info.mywinecellar.converter.AreaConverter}
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/edit")
    public MyWineCellar areaEditPut(@PathVariable Long areaId, @RequestBody(required = false) AreaDto request) {
        if (request != null) {
            Area edit = areaService.editArea(request, areaId);
            return new Builder().area(edit).build();
        } else {
            log.debug("area request was null for id {}", areaId);
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("area request for id %d was null", areaId));
        }
    }

    /**
     * Add producer to an area
     *
     * @param areaId  The id of the area
     * @param request Name is the only required field:
     *                {@link ProducerDto}
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/add-producer")
    public MyWineCellar areaAddProducerPost(@PathVariable Long areaId, @RequestBody(required = false) ProducerDto request) {
        if (request != null) {
            Area area = areaService.addProducer(areaId, request);
            return new Builder().area(area).build();
        } else {
            log.debug("producer request was null, trying to add to areaId {}", areaId);
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("producer request for area id %d", areaId));
        }
    }

    /**
     * Add a grape, or grapes, to an area
     *
     * @param areaId  The id of the area
     * @param grapeId Multiple id's or a single id
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/add-grape")
    public MyWineCellar areaAddGrape(@PathVariable Long areaId, @RequestParam(required = false) List<Long> grapeId) {
        if (grapeId != null) {
            Area area = areaService.findById(areaId);

            grapeId.forEach(id -> {
                Grape grape = grapeService.findById(id);
                grape.getAreas().add(area);
            });

            areaService.save(area);
            return new Builder().area(area).build();
        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("grape was null for area id %d", areaId));
        }
    }

}
