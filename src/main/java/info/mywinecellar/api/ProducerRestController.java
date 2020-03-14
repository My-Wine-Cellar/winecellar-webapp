/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.service.ProducerRestService;
import info.mywinecellar.model.Producer;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/producer/{producerId}")
public class ProducerRestController extends AbstractRestController {

    @Inject ProducerRestService restService;

    /**
     * PUT mapping to edit Producer
     *
     * @param request   Producer producer
     * @param producerId Long producerId
     * @return ResponseEntity.Accepted
     */
    @PutMapping("/edit")
    public ResponseEntity<?> producerEditPut(@PathVariable Long producerId, @RequestBody Producer request) {
        Producer update = producerService.findById(producerId);
        checkObjectNull(update);
        restService.updateProducer(update, request);
        return ResponseEntity.accepted().body("Updated " + update.toString());
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
    public ResponseEntity<?> producerImagePut(@PathVariable Long producerId, @RequestPart MultipartFile file)
            throws IOException {
        Producer save = producerService.findById(producerId);
        checkObjectNull(save);
        restService.checkFileThenSave(save, file);
        return ResponseEntity.accepted().body("Image upload success");
    }

}
