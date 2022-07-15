/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.model.Country;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Optional.ofNullable(country)
                .map(CountryDto::new)
                .orElse(null);
    }

    /**
     * Convert a list of {@link Country}'s to a list of {@link CountryDto} objects
     *
     * @param countries this list of countries
     * @return the list of {@link CountryDto}'s sorted by name, or an empty list
     */
    public static List<CountryDto> toDto(Set<Country> countries) {
        return Stream.ofNullable(countries)
                .flatMap(Collection::stream)
                .map(CountryConverter::toDto)
                .sorted(Comparator.comparing(CountryDto::getName))
                .collect(Collectors.toList());
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
