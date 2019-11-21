/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.security.service.UserService;
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
import info.mywinecellar.service.WineService;
import info.mywinecellar.service.WishlistService;
import info.mywinecellar.ui.AreaUI;
import info.mywinecellar.ui.AreaUIFactory;
import info.mywinecellar.ui.CountryUI;
import info.mywinecellar.ui.CountryUIFactory;
import info.mywinecellar.ui.ProducerUI;
import info.mywinecellar.ui.ProducerUIFactory;
import info.mywinecellar.ui.RegionUI;
import info.mywinecellar.ui.RegionUIFactory;

import java.security.Principal;

import javax.inject.Inject;

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
    @Inject protected ColorService colorService;
    @Inject protected TypeService typeService;

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
