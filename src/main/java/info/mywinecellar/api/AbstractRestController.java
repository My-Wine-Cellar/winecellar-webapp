/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.CountryService;
import info.mywinecellar.service.GrapeService;
import info.mywinecellar.service.ProducerService;
import info.mywinecellar.service.RegionService;
import info.mywinecellar.service.WineService;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

abstract class AbstractRestController {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject GrapeService grapeService;
    @Inject CountryService countryService;
    @Inject RegionService regionService;
    @Inject AreaService areaService;
    @Inject ProducerService producerService;
    @Inject WineService wineService;

    void checkObjectNull(Object o) {
        if (o == null) {
            log.info("==== This object is null ====");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    void checkObjectListNull(List<?> list) {
        if (list.isEmpty()) {
            log.info("==== This list of objects is null ====");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
