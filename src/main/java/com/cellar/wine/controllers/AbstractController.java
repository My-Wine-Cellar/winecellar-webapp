package com.cellar.wine.controllers;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Country;
import com.cellar.wine.models.Region;
import com.cellar.wine.models.Producer;
import com.cellar.wine.nav.Paths;
import com.cellar.wine.security.User;
import com.cellar.wine.security.UserService;
import com.cellar.wine.services.AreaService;
import com.cellar.wine.services.CountryService;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.RegionService;
import com.cellar.wine.ui.AreaUI;
import com.cellar.wine.ui.AreaUIFactory;
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.CountryUIFactory;
import com.cellar.wine.ui.ProducerUI;
import com.cellar.wine.ui.ProducerUIFactory;
import com.cellar.wine.ui.RegionUI;
import com.cellar.wine.ui.RegionUIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public abstract class AbstractController {

    @Inject
    protected AreaService areaService;

    @Inject
    protected CountryService countryService;

    @Inject
    protected ProducerService producerService;

    @Inject
    protected RegionService regionService;

    @Inject
    protected UserService userService;

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
}
