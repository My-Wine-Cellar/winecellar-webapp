/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.model.Country;
import info.mywinecellar.ui.CountryUI;
import info.mywinecellar.ui.CountryUISorter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CountryConverter {

    /**
     * Convert Country entity to CountryUI
     *
     * @param country Country country
     * @return CountryUI object
     */
    public CountryUI toUI(Country country) {
        if (country == null) {
            throw new IllegalStateException("Country is null");
        }
        return new CountryUI(country);
    }

    /**
     * Convert a list of Countries to a list of CountryUI objects
     *
     * @param countries List<Country> countries
     * @return List<CountryUI> countryUI's
     */
    public List<CountryUI> toUIs(List<Country> countries) {
        if (countries == null) {
            throw new IllegalStateException("Country list is null");
        }
        List<CountryUI> result = new ArrayList<>();
        countries.forEach(country -> result.add(toUI(country)));
        result.sort(new CountryUISorter());
        return result;
    }

    /**
     * Update and convert Country entity from CountryUI to prepare for save
     *
     * @param entity Country entity
     * @param ui     CountryUI ui
     * @return Country entity
     */
    public Country toEntity(Country entity, CountryUI ui) {
        if (entity == null) {
            entity = new Country(ui.getName(), ui.getDescription(), ui.getWeblink(), ui.getFlag());
        } else {
            entity.setDescription(ui.getDescription());
            entity.setWeblink(ui.getWeblink());
        }
        return entity;
    }
}
