/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.json;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Country;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Region;
import info.mywinecellar.model.Wine;

/**
 * Builder
 */
public class Builder {

    private AreaConverter areaConverter;
    private CountryConverter countryConverter;
    private ProducerConverter producerConverter;
    private RegionConverter regionConverter;
    private WineConverter wineConverter;

    private MyWineCellar json;

    /**
     * Constructor
     */
    public Builder() {
        areaConverter = new AreaConverter();
        countryConverter = new CountryConverter();
        producerConverter = new ProducerConverter();
        regionConverter = new RegionConverter();
        wineConverter = new WineConverter();

        json = new MyWineCellar();
    }

    /**
     * Add an area
     * @param a The area
     * @return The builder
     */
    public Builder area(Area a) {
        if (!json.hasArea(a.getId())) {
            json.addArea(areaConverter.toDto(a));
        }
        return this;
    }

    /**
     * Add a country
     * @param c The country
     * @return The builder
     */
    public Builder country(Country c) {
        if (!json.hasCountry(c.getId())) {
            json.addCountry(countryConverter.toDto(c));
        }
        return this;
    }

    /**
     * Add a producer
     * @param p The producer
     * @return The builder
     */
    public Builder producer(Producer p) {
        if (!json.hasProducer(p.getId())) {
            json.addProducer(producerConverter.toDto(p));
        }
        return this;
    }

    /**
     * Add a region
     * @param r The region
     * @return The builder
     */
    public Builder region(Region r) {
        if (!json.hasRegion(r.getId())) {
            json.addRegion(regionConverter.toDto(r));
        }
        return this;
    }

    /**
     * Add a wine
     * @param w The wine
     * @return The builder
     */
    public Builder wine(Wine w) {
        if (!json.hasWine(w.getId())) {
            json.addWine(wineConverter.toDto(w));
        }
        return this;
    }

    /**
     * Build the MyWineCellar instance
     * @return The result
     */
    public MyWineCellar build() {
        return json;
    }
}

