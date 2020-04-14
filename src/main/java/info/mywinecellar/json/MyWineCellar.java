/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.json;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.AreaDtoSorter;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.dto.CountryDtoSorter;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.dto.ProducerDtoSorter;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.dto.RegionDtoSorter;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.dto.WineDtoSorter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

/**
 * MyWineCellar
 */
@Data
public class MyWineCellar implements Serializable {

    private boolean sorted;

    private List<AreaDto> areas;
    private List<CountryDto> countries;
    private List<ProducerDto> producers;
    private List<RegionDto> regions;
    private List<WineDto> wines;

    /**
     * Constructor
     */
    MyWineCellar() {
        sorted = false;
    }

    /**
     * Get the areas
     * @return The list
     */
    public List<AreaDto> getAreas() {
        if (!sorted) {
            sortAll();
        }
        return areas;
    }

    /**
     * Get the countries
     * @return The list
     */
    public List<CountryDto> getCountries() {
        if (!sorted) {
            sortAll();
        }
        return countries;
    }

    /**
     * Get the producers
     * @return The list
     */
    public List<ProducerDto> getProducers() {
        if (!sorted) {
            sortAll();
        }
        return producers;
    }

    /**
     * Get the regions
     * @return The list
     */
    public List<RegionDto> getRegions() {
        if (!sorted) {
            sortAll();
        }
        return regions;
    }

    /**
     * Get the wines
     * @return The list
     */
    public List<WineDto> getWines() {
        if (!sorted) {
            sortAll();
        }
        return wines;
    }

    void addArea(AreaDto a) {
        if (areas == null) {
            areas = new ArrayList<>();
        }

        if (!areas.contains(a)) {
            areas.add(a);
        }
    }

    void addCountry(CountryDto c) {
        if (countries == null) {
            countries = new ArrayList<>();
        }

        if (!countries.contains(c)) {
            countries.add(c);
        }
    }

    void addRegion(RegionDto r) {
        if (regions == null) {
            regions = new ArrayList<>();
        }

        if (!regions.contains(r)) {
            regions.add(r);
        }
    }

    void addProducer(ProducerDto p) {
        if (producers == null) {
            producers = new ArrayList<>();
        }

        if (!producers.contains(p)) {
            producers.add(p);
        }
    }

    void addWine(WineDto w) {
        if (wines == null) {
            wines = new ArrayList<>();
        }

        if (!wines.contains(w)) {
            wines.add(w);
        }
    }

    private void sortAll() {
        if (areas != null) {
            Collections.sort(areas, new AreaDtoSorter());
        }

        if (countries != null) {
            Collections.sort(countries, new CountryDtoSorter());
        }

        if (producers != null) {
            Collections.sort(producers, new ProducerDtoSorter());
        }

        if (regions != null) {
            Collections.sort(regions, new RegionDtoSorter());
        }

        if (wines != null) {
            Collections.sort(wines, new WineDtoSorter());
        }

        sorted = true;
    }
}
