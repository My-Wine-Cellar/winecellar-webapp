/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.CountryService;
import info.mywinecellar.service.ProducerService;
import info.mywinecellar.service.RegionService;
import info.mywinecellar.service.WineService;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    AreaService areaService;

    @Inject
    CountryService countryService;

    @Inject
    ProducerService producerService;

    @Inject
    RegionService regionService;

    @Inject
    WineService wineService;

    /**
     * Default constructor
     */
    protected AbstractController() {
    }

    protected CountryDto getCountryDto(Long countryId) {
        return CountryConverter.toDto(countryService.findById(countryId));
    }

    protected RegionDto getRegionDto(Long regionId) {
        return RegionConverter.toDto(regionService.findById(regionId));
    }

    protected AreaDto getAreaDto(Long areaId) {
        return AreaConverter.toDto(areaService.findById(areaId));
    }

    protected String redirectCountry(Long countryId) {
        return redirectCountry(countryService.findById(countryId));
    }

    protected String redirectCountry(Country country) {
        CountryDto countryDto = CountryConverter.toDto(country);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey();
    }

    protected String redirectRegion(Long countryId, Long regionId) {
        return redirectRegion(countryId, regionService.findById(regionId));
    }

    protected String redirectRegion(Long countryId, Region region) {
        Country country = countryService.findById(countryId);
        CountryDto countryDto = CountryConverter.toDto(country);
        RegionDto regionDto = RegionConverter.toDto(region);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey() + "/" + regionDto.getKey();
    }

    protected String redirectArea(Long countryId, Long regionId, Long areaId) {
        return redirectArea(countryId, regionId, areaService.findById(areaId));
    }

    protected String redirectArea(Long countryId, Long regionId, Area area) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);

        CountryDto countryDto = CountryConverter.toDto(country);
        RegionDto regionDto = RegionConverter.toDto(region);
        AreaDto areaDto = AreaConverter.toDto(area);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey() + "/" + regionDto.getKey() + "/" + areaDto.getKey();
    }

    protected String redirectProducer(Long countryId, Long regionId, Long areaId, Long producerId) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);
        Area area = areaService.findById(areaId);
        Producer producer = producerService.findById(producerId);

        CountryDto countryDto = CountryConverter.toDto(country);
        RegionDto regionDto = RegionConverter.toDto(region);
        AreaDto areaDto = AreaConverter.toDto(area);
        ProducerDto producerDto = ProducerConverter.toDto(producer);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey() + "/" + regionDto.getKey() + "/" +
                areaDto.getKey() + "/" + producerDto.getKey();
    }

    protected String principalNull(Principal principal) {
        log.debug("Principal is null");
        return principal == null ? Paths.REDIRECT_ROOT : Paths.ERROR_PAGE;
    }
}
