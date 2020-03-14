/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api.service;

import info.mywinecellar.model.Grape;
import info.mywinecellar.service.GrapeService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrapeRestService {

    @Inject GrapeService grapeService;

    /**
     * Update a Grape from the request
     *
     * @param update  Grape update
     * @param request Grape request
     */
    public void updateGrape(Grape update, Grape request) {
        if (request.getDescription() == null) {
            update.setDescription(update.getDescription());
        } else {
            update.setDescription(request.getDescription());
        }
        if (request.getWeblink() == null) {
            update.setWeblink(update.getWeblink());
        } else {
            update.setWeblink(request.getWeblink());
        }
        grapeService.save(update);
        log.info("==== Updated {} ====", update.toString());
    }
}
