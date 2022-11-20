/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Region;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Area} and {@link AreaDto} conversion
 */
public final class AreaConverter {

    private AreaConverter() {
    }

    /**
     * Convert a collection of {@link Area} objects to {@link AreaDto} objects
     * filter...
     *
     * @param areas The areas
     * @return The Dto objects
     */
    public static List<AreaDto> toDto(Set<Area> areas) {
        //sort by name
        List<AreaDto> areaDtos = areas.stream()
                .map(AreaConverter::toDto)
                .sorted(Comparator.comparing(AreaDto::getName))
                .collect(Collectors.toList());

        //get the region's name from our area
        String region = areas.stream()
                .map(Area::getRegions)
                .flatMap(Collection::stream)
                .map(Region::getName)
                .findFirst()
                .map(Objects::toString)
                .orElse(null);

        //find the dto that matches our region's name
        AreaDto matches = areaDtos.stream()
                .filter(areaDto -> areaDto.getName().equals(region))
                .findAny()
                .orElse(null);

        if (matches != null) {
            areaDtos.remove(matches);
            areaDtos.add(0, matches);
        }

        return areaDtos;
    }


    /**
     * Convert entity to dto
     *
     * @param a An area
     * @return dto object
     */
    public static AreaDto toDto(Area a) {
        return Optional.ofNullable(a)
                .map(AreaDto::new)
                .orElse(null);
    }

    /**
     * Create an Area entity
     *
     * @param a   An area
     * @param dto The Dto object
     * @return The area
     */
    public static Area toEntity(Area a, AreaDto dto) {
        if (a == null) {
            a = new Area(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            a.setDescription(dto.getDescription());
            a.setWeblink(dto.getWeblink());
        }
        return a;
    }
}
