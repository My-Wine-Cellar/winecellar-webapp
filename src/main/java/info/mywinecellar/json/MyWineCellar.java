/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.json;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.dto.WineDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * MyWineCellar
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName(value = "mywinecellar")
public class MyWineCellar implements Serializable {

    @JsonSerialize(using = GenericSerializer.class)
    private List<AreaDto> areas;

    @JsonSerialize(using = GenericSerializer.class)
    private List<CountryDto> countries;

    @JsonSerialize(using = GenericSerializer.class)
    private List<ProducerDto> producers;

    @JsonSerialize(using = GenericSerializer.class)
    private List<RegionDto> regions;

    @JsonSerialize(using = GenericSerializer.class)
    private List<WineDto> wines;

    /**
     * Constructor
     */
    MyWineCellar() {
    }

    /**
     * Get the areas
     *
     * @return The list
     */
    public List<AreaDto> getAreas() {
        return areas;
    }

    /**
     * Get the countries
     *
     * @return The list
     */
    public List<CountryDto> getCountries() {
        return countries;
    }

    /**
     * Get the producers
     *
     * @return The list
     */
    public List<ProducerDto> getProducers() {
        return producers;
    }

    /**
     * Get the regions
     *
     * @return The list
     */
    public List<RegionDto> getRegions() {
        return regions;
    }

    /**
     * Get the wines
     *
     * @return The list
     */
    public List<WineDto> getWines() {
        return wines;
    }

    void addArea(AreaDto a) {
        if (areas == null) {
            areas = new ArrayList<>();
        }

        areas.add(a);
    }

    boolean hasArea(Long id) {
        if (areas != null) {
            for (AreaDto a : areas) {
                if (a.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    void addCountry(CountryDto c) {
        if (countries == null) {
            countries = new ArrayList<>();
        }

        countries.add(c);
    }

    boolean hasCountry(Long id) {
        if (countries != null) {
            for (CountryDto c : countries) {
                if (c.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    void addRegion(RegionDto r) {
        if (regions == null) {
            regions = new ArrayList<>();
        }

        regions.add(r);
    }

    boolean hasRegion(Long id) {
        if (regions != null) {
            for (RegionDto r : regions) {
                if (r.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    void addProducer(ProducerDto p) {
        if (producers == null) {
            producers = new ArrayList<>();
        }

        producers.add(p);
    }

    boolean hasProducer(Long id) {
        if (producers != null) {
            for (ProducerDto p : producers) {
                if (p.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    void addWine(WineDto w) {
        if (wines == null) {
            wines = new ArrayList<>();
        }

        wines.add(w);
    }

    boolean hasWine(Long id) {
        if (wines != null) {
            for (WineDto w : wines) {
                if (w.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }
}
