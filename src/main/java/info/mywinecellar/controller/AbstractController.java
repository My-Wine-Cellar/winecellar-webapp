/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.BarrelConverter;
import info.mywinecellar.converter.BottleConverter;
import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.converter.GrapeConverter;
import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.converter.ReviewConverter;
import info.mywinecellar.converter.TastedConverter;
import info.mywinecellar.converter.TastingNotesConverter;
import info.mywinecellar.converter.UserConverter;
import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.converter.WishlistConverter;
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
import info.mywinecellar.service.BarrelComponentService;
import info.mywinecellar.service.BarrelService;
import info.mywinecellar.service.BottleService;
import info.mywinecellar.service.ClosureService;
import info.mywinecellar.service.ColorService;
import info.mywinecellar.service.CountryService;
import info.mywinecellar.service.FermentationService;
import info.mywinecellar.service.GrapeComponentService;
import info.mywinecellar.service.GrapeService;
import info.mywinecellar.service.MacerationService;
import info.mywinecellar.service.ProducerService;
import info.mywinecellar.service.RegionService;
import info.mywinecellar.service.ReviewService;
import info.mywinecellar.service.ShapeService;
import info.mywinecellar.service.TastedService;
import info.mywinecellar.service.TastingNotesService;
import info.mywinecellar.service.TypeService;
import info.mywinecellar.service.UserService;
import info.mywinecellar.service.WineService;
import info.mywinecellar.service.WishlistService;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {

    /**
     * Logger
     */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * AreaConverter
     */
    @Inject
    protected AreaConverter areaConverter;

    /**
     * AreaService
     */
    @Inject
    protected AreaService areaService;

    /**
     * CountryConverter
     */
    @Inject
    protected CountryConverter countryConverter;

    /**
     * CountryService
     */
    @Inject
    protected CountryService countryService;

    /**
     * ProducerConverter
     */
    @Inject
    protected ProducerConverter producerConverter;

    /**
     * ProducerService
     */
    @Inject
    protected ProducerService producerService;

    /**
     * RegionConverter
     */
    @Inject
    protected RegionConverter regionConverter;

    /**
     * RegionService
     */
    @Inject
    protected RegionService regionService;

    /**
     * UserConverter
     */
    @Inject
    protected UserConverter userConverter;

    /**
     * UserService
     */
    @Inject
    protected UserService userService;

    /**
     * GrapeConverter
     */
    @Inject
    protected GrapeConverter grapeConverter;

    /**
     * GrapeService
     */
    @Inject
    protected GrapeService grapeService;

    /**
     * BottleConverter
     */
    @Inject
    protected BottleConverter bottleConverter;

    /**
     * BottleService
     */
    @Inject
    protected BottleService bottleService;

    /**
     * WineConverter
     */
    @Inject
    protected WineConverter wineConverter;

    /**
     * WineService
     */
    @Inject
    protected WineService wineService;

    /**
     * TastedConverter
     */
    @Inject
    protected TastedConverter tastedConverter;

    /**
     * TastedService
     */
    @Inject
    protected TastedService tastedService;

    /**
     * ReviewConverter
     */
    @Inject
    protected ReviewConverter reviewConverter;

    /**
     * ReviewService
     */
    @Inject
    protected ReviewService reviewService;

    /**
     * BarrelService
     */
    @Inject
    protected BarrelService barrelService;

    /**
     * TastingNotesConverter
     */
    @Inject
    protected TastingNotesConverter tastingNotesConverter;

    /**
     * TastingNotesService
     */
    @Inject
    protected TastingNotesService tastingNotesService;

    /**
     * WishlistConverter
     */
    @Inject
    protected WishlistConverter wishlistConverter;

    /**
     * WishlistService
     */
    @Inject
    protected WishlistService wishlistService;

    /**
     * GrapeComponentService
     */
    @Inject
    protected GrapeComponentService grapeComponentService;

    /**
     * BarrleConverter
     */
    @Inject
    protected BarrelConverter barrelConverter;

    /**
     * BarrelComponentService
     */
    @Inject
    protected BarrelComponentService barrelComponentService;

    /**
     * ClosureService
     */
    @Inject
    protected ClosureService closureService;

    /**
     * ShapeService
     */
    @Inject
    protected ShapeService shapeService;

    /**
     * MacerationService
     */
    @Inject
    protected MacerationService macerationService;

    /**
     * FermentationService
     */
    @Inject
    protected FermentationService fermentationService;

    /**
     * ColorService
     */
    @Inject
    protected ColorService colorService;

    /**
     * TypeService
     */
    @Inject
    protected TypeService typeService;

    /**
     * Default constructor
     */
    public AbstractController() {
    }

    protected CountryDto getCountryDto(Long countryId) {
        return countryConverter.toDto(countryService.findById(countryId));
    }

    protected RegionDto getRegionDto(Long regionId) {
        return regionConverter.toDto(regionService.findById(regionId));
    }

    protected AreaDto getAreaDto(Long areaId) {
        return areaConverter.toDto(areaService.findById(areaId));
    }

    protected String redirectCountry(Long countryId) {
        return redirectCountry(countryService.findById(countryId));
    }

    protected String redirectCountry(Country country) {
        CountryDto countryDto = countryConverter.toDto(country);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey();
    }

    protected String redirectRegion(Long countryId, Long regionId) {
        return redirectRegion(countryId, regionService.findById(regionId));
    }

    protected String redirectRegion(Long countryId, Region region) {
        Country country = countryService.findById(countryId);
        CountryDto countryDto = countryConverter.toDto(country);
        RegionDto regionDto = regionConverter.toDto(region);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey() + "/" + regionDto.getKey();
    }

    protected String redirectArea(Long countryId, Long regionId, Long areaId) {
        return redirectArea(countryId, regionId, areaService.findById(areaId));
    }

    protected String redirectArea(Long countryId, Long regionId, Area area) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);

        CountryDto countryDto = countryConverter.toDto(country);
        RegionDto regionDto = regionConverter.toDto(region);
        AreaDto areaDto = areaConverter.toDto(area);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey() + "/" + regionDto.getKey() + "/" + areaDto.getKey();
    }

    protected String redirectProducer(Long countryId, Long regionId, Long areaId, Long producerId) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);
        Area area = areaService.findById(areaId);
        Producer producer = producerService.findById(producerId);

        CountryDto countryDto = countryConverter.toDto(country);
        RegionDto regionDto = regionConverter.toDto(region);
        AreaDto areaDto = areaConverter.toDto(area);
        ProducerDto producerDto = producerConverter.toDto(producer);

        return Paths.REDIRECT_ROOT + "d/" + countryDto.getKey() + "/" + regionDto.getKey() + "/" +
                areaDto.getKey() + "/" + producerDto.getKey();
    }

    protected String principalNull(Principal principal) {
        log.debug("Principal is null");
        return principal == null ? Paths.REDIRECT_ROOT : Paths.ERROR_PAGE;
    }
}
