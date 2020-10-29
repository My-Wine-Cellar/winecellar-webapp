/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Producer;
import info.mywinecellar.service.ProducerService;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/producer/{producerId}")
public class ProducerRestController {

    @Inject
    ProducerService producerService;

    /**
     * Edit a producer
     *
     * @param request    A variety of fields are available in the request:
     *                   {@link ProducerDto}
     *                   {@link info.mywinecellar.converter.ProducerConverter}
     * @param producerId The id of the producer to edit
     * @return MyWineCellar JSON envelope and the producer
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/edit")
    public MyWineCellar producerEditPut(@PathVariable Long producerId, @RequestBody ProducerDto request) {
        Producer entity = producerService.editProducer(request, producerId);
        log.info("Updated {} {} ", entity.toString(), entity.getName());
        return new Builder().producer(entity).build();
    }

    /**
     * Add an image to the producer
     *
     * @param producerId The id of the producer
     * @param file       MultipartFile as a jpg, png, etc.
     * @return MyWineCellar JSON envelope and the producer
     * @throws IOException exception
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MyWineCellar producerImagePut(@PathVariable Long producerId, @RequestPart MultipartFile file)
            throws IOException {
        Producer entity = producerService.findById(producerId);
        if (file.getBytes().length >= 5242880L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot exceed 5MB");
        }
        entity.setImage(file.getBytes());
        producerService.save(entity);
        log.info("Image added to {} {} ", entity.toString(), entity.getName());
        return new Builder().producer(entity).build();
    }

}
