/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Wine;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/wine")
public class WineRestController extends AbstractRestController {

    @Inject
    WineConverter wineConverter;

    /**
     * POST mapping to add a new Wine
     *
     * @param wineDto    WineDto dto
     * @param producerId Long producerId
     * @param shapeId    Long shapeId
     * @param colorId    Long colorId
     * @param typeId     Long typeId
     * @param closureId  Long closureId
     * @return MyWineCellar
     */
    @PostMapping("/new")
    public MyWineCellar wineNewPost(@RequestBody WineDto wineDto, @RequestParam Long producerId,
                                    @RequestParam(defaultValue = "1") Long shapeId,
                                    @RequestParam(defaultValue = "1") Long colorId,
                                    @RequestParam(defaultValue = "1") Long typeId,
                                    @RequestParam(defaultValue = "1") Long closureId) {

        Wine entity = wineConverter.toEntity(null, wineDto);

        Producer producer = producerService.findById(producerId);
        entity.setProducer(producer);
        producer.getWines().add(entity);

        entity.setShape(shapeService.findById(shapeId));
        entity.setColor(colorService.findById(colorId));
        entity.setType(typeService.findById(typeId));
        entity.setClosure(closureService.findById(closureId));
        wineService.save(entity);
        log.info("==== Added new {} ====", entity.toString());

        Builder builder = new Builder();
        builder.wine(entity);
        return builder.build();
    }

    /**
     * PUT mapping to edit Wine
     *
     * @param wineId  Long wineId
     * @param wineDto Wine wineRequest
     * @return MyWineCellar
     */
    @PutMapping("/{wineId}/edit")
    public MyWineCellar wineEditPut(@PathVariable Long wineId, @RequestBody WineDto wineDto) {
        Wine entity = wineConverter.toEntity(wineService.findById(wineId), wineDto);
        wineService.update(entity);
        log.info("==== Updated {} ====", entity.toString());

        Builder builder = new Builder();
        builder.wine(entity);
        return builder.build();
    }

    /**
     * PUT mapping to add image to Wine
     *
     * @param wineId Long wineId
     * @param file   MultipartFile file
     * @return MyWineCellar
     * @throws IOException exception
     */
    @PutMapping(value = "/{wineId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MyWineCellar wineImagePut(@PathVariable Long wineId, @RequestPart MultipartFile file)
            throws IOException {
        Wine entity = wineService.findById(wineId);
        if (file.getBytes().length >= 5242880L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot exceed 5MB");
        }
        entity.setImage(file.getBytes());
        wineService.update(entity);
        log.info("==== Image added to {} ====", entity.toString());

        Builder builder = new Builder();
        builder.wine(entity);
        return builder.build();
    }

}
