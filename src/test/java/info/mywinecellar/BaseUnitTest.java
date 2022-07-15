/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Closure;
import info.mywinecellar.model.Color;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Grape;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Shape;
import info.mywinecellar.model.Type;
import info.mywinecellar.model.Wine;
import info.mywinecellar.service.AreaService;
import info.mywinecellar.service.CountryService;
import info.mywinecellar.service.ProducerService;
import info.mywinecellar.service.RegionService;
import info.mywinecellar.service.WineService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

public abstract class BaseUnitTest {

    /**
     * Country service mock
     */
    @Mock
    protected CountryService countryService;

    /**
     * Region service mock
     */
    @Mock
    protected RegionService regionService;

    /**
     * Area service mock
     */
    @Mock
    protected AreaService areaService;

    /**
     * Producer service mock
     */
    @Mock
    protected ProducerService producerService;

    /**
     * Wine service mock
     */
    @Mock
    protected WineService wineService;

    /**
     * setup for our countries
     */
    protected Set<Country> countries = new HashSet<>();

    /**
     * setup a country
     */
    protected Country us;

    /**
     * setup a country
     */
    protected Country it;

    /**
     * setup for our regions
     */
    protected Set<Region> usRegions = new HashSet<>();

    /**
     * setup a region
     */
    protected Region cali;

    /**
     * setup a region
     */
    protected Region wash;

    /**
     * setup for our areas
     */
    protected Set<Area> caliAreas = new HashSet<>();

    /**
     * setup an area
     */
    protected Area napa;

    /**
     * setup an area
     */
    protected Area sonoma;

    /**
     * setup for our producers
     */
    protected Set<Producer> producers = new HashSet<>();

    /**
     * setup a producer
     */
    protected Producer opusOne;

    /**
     * setup our grapes
     */
    protected Set<Grape> grapes = new HashSet<>();

    /**
     * setup a grape
     */
    protected Grape pinotNoir;

    /**
     * setup a grape
     */
    protected Grape albarino;

    /**
     * setup our wines
     */
    protected Set<Wine> wines = new HashSet<>();

    /**
     * setup a wine
     */
    protected Wine opusOne2015;

    /**
     * setup closure
     */
    protected Closure closure;

    /**
     * setup color
     */
    protected Color color;

    /**
     * setup shape
     */
    protected Shape shape;

    /**
     * setup type
     */
    protected Type type;

    @BeforeEach
    void init() {
        countries = setupCountries();
        usRegions = setupRegions();
        caliAreas = setupAreas();
        producers = setupProducers();
        grapes = setupGrapes();
        wines = setupWines();
    }

    private Set<Country> setupCountries() {
        us = new Country();
        us.setId(1L);
        us.setName("United States");
        it = new Country();
        it.setName("Italy");
        it.setId(2L);
        countries.add(us);
        countries.add(it);
        return countries;
    }

    /**
     * Setup our Regions/States
     * @return Regions
     */
    private Set<Region> setupRegions() {
        cali = new Region();
        cali.setId(1L);
        cali.setName("California");
        cali.setCountry(us);
        cali.setAreas(caliAreas);
        usRegions.add(cali);
        wash = new Region();
        wash.setId(2L);
        wash.setName("Washington");
        wash.setCountry(us);
        usRegions.add(wash);
        us.setRegions(usRegions);
        return usRegions;
    }

    /**
     * Setup our AVA/AOC/DOC's
     *
     * @return Areas
     */
    private Set<Area> setupAreas() {
        napa = new Area();
        napa.setId(1L);
        napa.setName("Napa Valley AVA");
        napa.setRegions(Collections.singleton(cali));
        napa.setProducers(producers);
        napa.setPrimaryGrapes(grapes);
        caliAreas.add(napa);
        sonoma = new Area();
        sonoma.setId(2L);
        sonoma.setName("Sonoma Valley AVA");
        sonoma.setPrimaryGrapes(grapes);
        sonoma.setRegions(Collections.singleton(cali));
        sonoma.setProducers(Collections.emptySet());
        caliAreas.add(sonoma);
        cali.setCountry(us);
        cali.setAreas(caliAreas);
        wash.setAreas(Collections.emptySet());
        return caliAreas;
    }

    private Set<Producer> setupProducers() {
        opusOne = new Producer();
        opusOne.setId(1L);
        opusOne.setName("Opus One");
        opusOne.setAreas(Collections.singleton(napa));
        opusOne.setWines(Collections.emptySet()); //todo set a wine here???
        producers.add(opusOne);
        return producers;
    }

    private Set<Grape> setupGrapes() {
        pinotNoir = new Grape();
        pinotNoir.setId(1L);
        pinotNoir.setName("Pinot Noir");
        pinotNoir.setAreas(caliAreas);
        pinotNoir.setColor("Red");
        grapes.add(pinotNoir);
        albarino = new Grape();
        albarino.setId(2L);
        albarino.setName("Albarino");
        albarino.setAreas(caliAreas);
        albarino.setColor("White");
        grapes.add(albarino);
        return grapes;
    }

    private Set<Wine> setupWines() {
        opusOne2015 = Wine.createWineById(1L);
        opusOne2015.setName("Opus One");
        opusOne2015.setVintage(2015);
        opusOne2015.setSize(0.75f);
        opusOne2015.setProducer(opusOne);
        opusOne2015.setClosure(setupClosure());
        opusOne2015.setColor(setupColor());
        opusOne2015.setShape(setupShape());
        opusOne2015.setType(setupType());
        wines.add(opusOne2015);
        return wines;
    }

    private Closure setupClosure() {
        closure = new Closure();
        closure.setId(1L);
        closure.setName("Closure");
        closure.setWines(wines);
        return closure;
    }

    private Color setupColor() {
        color = new Color();
        color.setId(1L);
        color.setName("Color");
        color.setWines(wines);
        return color;
    }

    private Shape setupShape() {
        shape = new Shape();
        shape.setId(1L);
        shape.setName("Shape");
        shape.setWines(wines);
        return shape;
    }

    private Type setupType() {
        type = new Type();
        type.setId(1L);
        type.setName("Type");
        type.setWines(wines);
        return type;
    }
}
