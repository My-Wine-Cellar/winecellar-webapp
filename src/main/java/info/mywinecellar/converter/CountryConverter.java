/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.dto.CountryDtoSorter;
import info.mywinecellar.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link Country} and {@link CountryDto} conversion
 */
public final class CountryConverter {

    private CountryConverter() {
    }

    /**
     * Convert Country entity to CountryDto
     *
     * @param country Country country
     * @return CountryDto object
     */
    public static CountryDto toDto(Country country) {
        if (country == null) {
            throw new IllegalStateException("Country is null");
        }
        return new CountryDto(country);
    }

    /**
     * Convert a list of Countries to a list of CountryDto objects
     *
     * @param countries List<Country> countries
     * @return List<CountryDto> countryDto's
     */
    public static List<CountryDto> toDto(Set<Country> countries) {
        if (countries == null) {
            throw new IllegalStateException("Country list is null");
        }
        List<CountryDto> result = new ArrayList<>();
        countries.forEach(country -> result.add(toDto(country)));
        result.sort(new CountryDtoSorter());
        return result;
    }

    /**
     * Update and convert Country entity from CountryDto to prepare for save
     *
     * @param entity Country entity
     * @param dto    CountryDto dto
     * @return Country entity
     */
    public static Country toEntity(Country entity, CountryDto dto) {
        if (entity == null) {
            entity = new Country(dto.getName(), dto.getFlag(), dto.getDescription(), dto.getWeblink());
        } else {
            entity.setDescription(dto.getDescription());
            entity.setWeblink(dto.getWeblink());
        }
        return entity;
    }
}
