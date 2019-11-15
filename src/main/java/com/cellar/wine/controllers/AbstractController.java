/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Country;
import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Region;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.service.UserService;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.BarrelComponentService;
import com.cellar.wine.services.BarrelService;
import com.cellar.wine.services.BottleService;
import com.cellar.wine.services.ClosureService;
import com.cellar.wine.services.CountryService;
import com.cellar.wine.services.FermentationService;
import com.cellar.wine.services.GrapeComponentService;
import com.cellar.wine.services.GrapeService;
import com.cellar.wine.services.MacerationService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.RegionService;
import com.cellar.wine.services.ReviewService;
import com.cellar.wine.services.ShapeService;
import com.cellar.wine.services.TastedService;
import com.cellar.wine.services.TastingNotesService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.services.WishlistService;
import com.cellar.wine.ui.AreaUI;
import com.cellar.wine.ui.AreaUIFactory;
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.CountryUIFactory;
import com.cellar.wine.ui.ProducerUI;
import com.cellar.wine.ui.ProducerUIFactory;
import com.cellar.wine.ui.RegionUI;
import com.cellar.wine.ui.RegionUIFactory;

import javax.inject.Inject;
import java.security.Principal;

public abstract class AbstractController {

    @Inject protected AreaService areaService;
    @Inject protected CountryService countryService;
    @Inject protected ProducerService producerService;
    @Inject protected RegionService regionService;
    @Inject protected UserService userService;
    @Inject protected GrapeService grapeService;
    @Inject protected BottleService bottleService;
    @Inject protected WineService wineService;
    @Inject protected TastedService tastedService;
    @Inject protected ReviewService reviewService;
    @Inject protected BarrelService barrelService;
    @Inject protected TastingNotesService tastingNotesService;
    @Inject protected WishlistService wishlistService;
    @Inject protected GrapeComponentService grapeComponentService;
    @Inject protected BarrelComponentService barrelComponentService;
    @Inject protected ClosureService closureService;
    @Inject protected ShapeService shapeService;
    @Inject protected MacerationService macerationService;
    @Inject protected FermentationService fermentationService;

    public AbstractController() {
    }

    protected CountryUI getCountryUI(Long countryId) {
        return CountryUIFactory.instance().create(countryService.findById(countryId));
    }

    protected RegionUI getRegionUI(Long regionId) {
        return RegionUIFactory.instance().create(regionService.findById(regionId));
    }

    protected AreaUI getAreaUI(Long areaId) {
        return AreaUIFactory.instance().create(areaService.findById(areaId));
    }

    protected String redirectCountry(Long countryId) {
        return redirectCountry(countryService.findById(countryId));
    }

    protected String redirectCountry(Country country) {
        CountryUI cui = CountryUIFactory.instance().create(country);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey();
    }

    protected String redirectRegion(Long countryId, Long regionId) {
        return redirectRegion(countryId, regionService.findById(regionId));
    }

    protected String redirectRegion(Long countryId, Region region) {
        Country country = countryService.findById(countryId);
        CountryUI cui = CountryUIFactory.instance().create(country);
        RegionUI rui = RegionUIFactory.instance().create(region);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey() + "/" + rui.getKey();
    }

    protected String redirectArea(Long countryId, Long regionId, Long areaId) {
        return redirectArea(countryId, regionId, areaService.findById(areaId));
    }

    protected String redirectArea(Long countryId, Long regionId, Area area) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);

        CountryUI cui = CountryUIFactory.instance().create(country);
        RegionUI rui = RegionUIFactory.instance().create(region);
        AreaUI aui = AreaUIFactory.instance().create(area);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey();
    }

    protected String redirectProducer(Long countryId, Long regionId, Long areaId, Long producerId) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);
        Area area = areaService.findById(areaId);
        Producer producer = producerService.findById(producerId);

        CountryUI cui = CountryUIFactory.instance().create(country);
        RegionUI rui = RegionUIFactory.instance().create(region);
        AreaUI aui = AreaUIFactory.instance().create(area);
        ProducerUI pui = ProducerUIFactory.instance().create(producer);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey() + "/" + pui.getKey();
    }

    protected String principalNull(Principal principal) {
        return principal == null ? Paths.REDIRECT_ROOT : Paths.ERROR_PAGE;
    }
}
