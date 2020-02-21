/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api.service;

import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Wine;
import info.mywinecellar.service.ClosureService;
import info.mywinecellar.service.ColorService;
import info.mywinecellar.service.ProducerService;
import info.mywinecellar.service.ShapeService;
import info.mywinecellar.service.TypeService;
import info.mywinecellar.service.WineService;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WineRestService {

    @Inject WineService wineService;
    @Inject ProducerService producerService;
    @Inject ColorService colorService;
    @Inject TypeService typeService;
    @Inject ClosureService closureService;
    @Inject ShapeService shapeService;

    /**
     * Setup wine and its NotNull fields and relationships
     *
     * @param wine       Wine wine
     * @param producerId Long producerId
     * @param shapeId    Long shapeId
     * @param colorId    Long colorId
     * @param typeId     Long typeId
     * @param closureId  Long closureId
     * @return Wine
     */
    public Wine wineNewSetup(Wine wine, Long producerId, Long shapeId, Long colorId, Long typeId, Long closureId) {

        Producer producer = producerService.findById(producerId);

        wine.setProducer(producer);
        producer.getWines().add(wine);

        wine.setShape(shapeService.findById(shapeId));
        wine.setColor(colorService.findById(colorId));
        wine.setType(typeService.findById(typeId));
        wine.setClosure(closureService.findById(closureId));

        return wine;
    }

    /**
     * Update Wine from the request
     *
     * @param update  Wine update
     * @param request Wine request
     */
    public void updateWine(Wine update, Wine request) {

        if (request.getName() == null) {
            update.setName(update.getName());
        } else {
            update.setName(request.getName());
        }
        if (request.getSize() == null) {
            update.setSize(update.getSize());
        } else {
            update.setSize(request.getSize());
        }
        if (request.getVintage() == null) {
            update.setVintage(update.getVintage());
        } else {
            update.setVintage(request.getVintage());
        }
        if (request.getShape() == null) {
            update.setShape(update.getShape());
        } else {
            update.setShape(request.getShape());
        }
        if (request.getType() == null) {
            update.setType(update.getType());
        } else {
            update.setType(request.getType());
        }
        if (request.getColor() == null) {
            update.setColor(update.getColor());
        } else {
            update.setColor(request.getColor());
        }
        if (request.getClosure() == null) {
            update.setClosure(update.getClosure());
        } else {
            update.setClosure(request.getClosure());
        }

        update.setDescription(request.getDescription());
        update.setWeblink(request.getWeblink());
        update.setAlcohol(request.getAlcohol());
        update.setPH(request.getPH());
        update.setAcid(request.getAcid());
        update.setBottleAging(request.getBottleAging());

        wineService.save(update);
        log.info("Updated {} ", update.toString());
    }

    /**
     * Check that file size is less than 5MB then save
     *
     * @param saveWine Wine saveWine
     * @param file     MultipartFile file
     * @throws IOException exception
     */
    public void checkFileThenSave(Wine saveWine, MultipartFile file) throws IOException {
        if (file.getBytes().length >= 5242880L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot exceed 5MB");
        }
        saveWine.setImage(file.getBytes());
        wineService.save(saveWine);
        log.info("Imaged added to {} ", saveWine.toString());
    }

}
