/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.exception.ApiException;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Producer;
import info.mywinecellar.service.ProducerService;

import java.io.IOException;

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

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/producer/{producerId}")
public class ProducerRestController {

    private final ProducerService producerService;

    /**
     * Edit a producer
     *
     * @param producerId The id of the producer to edit
     * @param request    A variety of fields are available in the request:
     *                   {@link ProducerDto}
     *                   {@link info.mywinecellar.converter.ProducerConverter}
     * @return MyWineCellar JSON envelope and the producer
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/edit")
    public MyWineCellar producerEditPut(@PathVariable Long producerId, @RequestBody(required = false) ProducerDto request) {
        if (request != null) {
            Producer entity = producerService.editProducer(request, producerId);
            return new Builder().producer(entity).build();
        } else {
            log.debug("producer request was null for id {}", producerId);
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("producer request for id %d was null", producerId));
        }
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
    public MyWineCellar producerImagePut(@PathVariable Long producerId, @RequestPart MultipartFile file) throws IOException {
        if (file != null) {
            Producer entity = producerService.findById(producerId);
            if (file.getBytes().length >= 5242880L) {
                log.debug("image file exceeded length of 5242880L, size equal to 5MB");
                throw new ApiException(HttpStatus.BAD_REQUEST, "image cannot exceed 5MB");
            }
            entity.setImage(file.getBytes());
            producerService.save(entity);
            return new Builder().producer(entity).build();
        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST, "image file was not included in request");
        }
    }

}
