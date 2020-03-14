/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api.service;

import info.mywinecellar.model.Producer;
import info.mywinecellar.service.ProducerService;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProducerRestService {

    @Inject ProducerService producerService;

    /**
     * Update Producer from the request
     *
     * @param update  Producer update
     * @param request Producer request
     */
    public void updateProducer(Producer update, Producer request) {

        if (request.getName() == null) {
            update.setName(update.getName());
        } else {
            update.setName(request.getName());
        }
        if (request.getDescription() == null) {
            update.setDescription(update.getDescription());
        } else {
            update.setDescription(request.getDescription());
        }
        if (request.getWebsite() == null) {
            update.setWebsite(update.getWebsite());
        } else {
            update.setWebsite(request.getWebsite());
        }
        if (request.getEmail() == null) {
            update.setEmail(update.getEmail());
        } else {
            update.setEmail(request.getEmail());
        }
        if (request.getPhone() == null) {
            update.setPhone(update.getPhone());
        } else {
            update.setPhone(request.getPhone());
        }
        if (request.getFax() == null) {
            update.setFax(update.getFax());
        } else {
            update.setFax(request.getFax());
        }
        producerService.save(update);
        log.info("==== Updated {} ====", update.toString());
    }

    /**
     * Check that file size is less than 5MB then save
     *
     * @param save Producer save
     * @param file MultipartFile file
     * @throws IOException exception
     */
    public void checkFileThenSave(Producer save, MultipartFile file) throws IOException {
        if (file.getBytes().length >= 5242880L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot exceed 5MB");
        }
        save.setImage(file.getBytes());
        producerService.save(save);
        log.info("==== Image added to {} ====", save.toString());
    }

}
