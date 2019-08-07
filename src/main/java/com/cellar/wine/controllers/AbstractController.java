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
import com.cellar.wine.ui.CountryUI;
import com.cellar.wine.ui.ProducerUI;
import com.cellar.wine.ui.RegionUI;

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

    protected List<CountryUI> getCountryUIs(Set<Country> countries) {
        List<CountryUI> result = new ArrayList<>();
        for (Country c : countries) {
            result.add(getCountryUI(c));
        }
        return result;
    }

    protected CountryUI getCountryUI(Long countryId) {
        return getCountryUI(countryService.findById(countryId));
    }

    protected CountryUI getCountryUI(Country c) {
        if (c == null)
            return null;

        return new CountryUI(c);
    }

    protected List<RegionUI> getRegionUIs(List<Region> regions) {
        List<RegionUI> result = new ArrayList<>();
        for (Region r : regions) {
            result.add(getRegionUI(r));
        }
        return result;
    }

    protected RegionUI getRegionUI(Long regionId) {
        return getRegionUI(regionService.findById(regionId));
    }

    protected RegionUI getRegionUI(Region r) {
        if (r == null)
            return null;

        return new RegionUI(r);
    }

    protected List<AreaUI> getAreaUIs(List<Area> areas) {
        List<AreaUI> result = new ArrayList<>();
        for (Area a : areas) {
            result.add(getAreaUI(a));
        }
        return result;
    }

    protected AreaUI getAreaUI(Long areaId) {
        return getAreaUI(areaService.findById(areaId));
    }

    protected AreaUI getAreaUI(Area a) {
        if (a == null)
            return null;

        return new AreaUI(a);
    }

    protected List<ProducerUI> getProducerUIs(List<Producer> producers) {
        List<ProducerUI> result = new ArrayList<>();
        for (Producer p : producers) {
            result.add(getProducerUI(p));
        }
        return result;
    }

    protected ProducerUI getProducerUI(Producer p) {
        return new ProducerUI(p);
    }

    protected String redirectCountry(Long countryId) {
        return redirectCountry(countryService.findById(countryId));
    }

    protected String redirectCountry(Country country) {
        CountryUI cui = new CountryUI(country);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey();
    }

    protected String redirectRegion(Long countryId, Long regionId) {
        return redirectRegion(countryId, regionService.findById(regionId));
    }

    protected String redirectRegion(Long countryId, Region region) {
        Country country = countryService.findById(countryId);
        CountryUI cui = new CountryUI(country);
        RegionUI rui = new RegionUI(region);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey() + "/" + rui.getKey();
    }

    protected String redirectArea(Long countryId, Long regionId, Long areaId) {
        return redirectArea(countryId, regionId, areaService.findById(areaId));
    }

    protected String redirectArea(Long countryId, Long regionId, Area area) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);

        CountryUI cui = new CountryUI(country);
        RegionUI rui = new RegionUI(region);
        AreaUI aui = new AreaUI(area);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey();
    }

    protected String redirectProducer(Long countryId, Long regionId, Long areaId, Long producerId) {
        Country country = countryService.findById(countryId);
        Region region = regionService.findById(regionId);
        Area area = areaService.findById(areaId);
        Producer producer = producerService.findById(producerId);

        CountryUI cui = new CountryUI(country);
        RegionUI rui = new RegionUI(region);
        AreaUI aui = new AreaUI(area);
        ProducerUI pui = new ProducerUI(producer);

        return Paths.REDIRECT_ROOT + "d/" + cui.getKey() + "/" + rui.getKey() + "/" + aui.getKey() + "/" + pui.getKey();
    }
}
