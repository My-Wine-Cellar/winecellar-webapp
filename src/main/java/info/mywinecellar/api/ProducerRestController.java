/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.model.Producer;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/producer")
public class ProducerRestController extends AbstractRestController {

    /**
     * PUT mapping to edit Producer
     *
     * @param producer   Producer producer
     * @param producerId Long producerId
     * @return ResponseEntity.Accepted
     */
    @PutMapping("/{producerId}/edit")
    public ResponseEntity<?> producerEditPut(@RequestBody Producer producer, @PathVariable Long producerId) {
        Producer updateProducer = producerService.findById(producerId);
        checkObjectNull(updateProducer);
        producerService.update(producer, updateProducer);
        log.info("==== Updated producer #{} ====", updateProducer.getId());
        return ResponseEntity.accepted().body("Updated producer: " + updateProducer.getName());
    }

    /**
     * PUT mapping to add image to Producer
     *
     * @param producerId Long producerId
     * @param file       MultipartFile file
     * @return ResponseEntity.Accepted
     * @throws IOException exception
     */
    @PutMapping(value = "/{producerId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> producerImagePut(@PathVariable Long producerId, @RequestPart MultipartFile file)
            throws IOException {
        Producer saveProducer = producerService.findById(producerId);
        checkObjectNull(saveProducer);
        if (file.getBytes().length >= 5242880L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot exceed 5MB");
        }
        saveProducer.setImage(file.getBytes());
        producerService.save(saveProducer);
        log.info("Image added producer #{} ", producerId);
        return ResponseEntity.accepted().body("Image upload success");
    }

}
