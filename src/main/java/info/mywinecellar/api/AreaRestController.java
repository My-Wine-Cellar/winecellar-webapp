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
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Grape;

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

@RestController
@RequestMapping("/api/area/{areaId}")
public class AreaRestController extends AbstractRestController {

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
        Builder builder = new Builder();
        builder.area(area);
        return builder.build();
    }

    /**
     * Edit an area
     *
     * @param request Description and weblink are the only fields that can be edited:
     *                {@link AreaDto}
     *                {@link info.mywinecellar.converter.AreaConverter}
     * @param areaId  The id of the area to edit
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/edit")
    public MyWineCellar areaEditPut(@RequestBody AreaDto request, @PathVariable Long areaId) {
        checkObjectNull(request);
        Area area = areaService.editArea(request, areaId);
        log.info("Updated {} {} ", area.toString(), area.getName());

        Builder builder = new Builder();
        builder.area(area);
        return builder.build();
    }

    /**
     * Add producer to an area
     *
     * @param request Name is the only required field:
     *                {@link ProducerDto}
     * @param areaId  The id of the area
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/addProducer")
    public MyWineCellar areaAddProducerPost(@RequestBody ProducerDto request, @PathVariable Long areaId) {
        checkObjectNull(request);
        Area area = areaService.addProducer(areaId, request);
        log.info("Added {} to {} {} ", request.getName(), area.toString(), area.getName());

        Builder builder = new Builder();
        builder.area(area);
        return builder.build();
    }

    /**
     * Add a grape, or grapes, to an area
     *
     * @param grapeId Multiple id's or a single id
     * @param areaId  The id of the area
     * @return MyWineCellar JSON envelope and the area
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/addGrape")
    public MyWineCellar areaAddGrape(@RequestParam List<Long> grapeId, @PathVariable Long areaId) {
        Area area = areaService.findById(areaId);

        grapeId.forEach(id -> {
            Grape grape = grapeService.findById(id);
            grape.getAreas().add(area);
        });

        areaService.save(area);
        log.info("Added Grape/s {} to {} {} ", grapeId, area.toString(), area.getName());
        Builder builder = new Builder();
        builder.area(area);
        return builder.build();
    }
}
