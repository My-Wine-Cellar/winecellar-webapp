/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.json.Builder;
import info.mywinecellar.json.MyWineCellar;
import info.mywinecellar.model.Producer;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/producer/{producerId}")
public class ProducerRestController extends AbstractRestController {

    @Inject
    ProducerConverter producerConverter;

    /**
     * PUT mapping to edit Producer
     *
     * @param request    Producer producer
     * @param producerId Long producerId
     * @return ResponseEntity.Accepted
     */
    @PutMapping("/edit")
    public MyWineCellar producerEditPut(@PathVariable Long producerId, @RequestBody ProducerDto request) {
        Producer entity = producerService.editProducer(request, producerId);
        Builder builder = new Builder();
        builder.producer(entity);
        return builder.build();
    }

    /**
     * PUT mapping to add image to Producer
     *
     * @param producerId Long producerId
     * @param file       MultipartFile file
     * @return ResponseEntity.Accepted
     * @throws IOException exception
     */
    @PutMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MyWineCellar producerImagePut(@PathVariable Long producerId, @RequestPart MultipartFile file)
            throws IOException {
        Producer entity = producerService.findById(producerId);
        if (file.getBytes().length >= 5242880L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot exceed 5MB");
        }
        entity.setImage(file.getBytes());
        producerService.save(entity);
        Builder builder = new Builder();
        builder.producer(entity);
        return builder.build();
    }

}
