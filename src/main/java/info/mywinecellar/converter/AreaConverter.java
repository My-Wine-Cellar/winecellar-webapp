/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.AreaDtoSorter;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link Area} and {@link AreaDto} conversion
 */
public final class AreaConverter {

    private AreaConverter() {
    }

    /**
     * Create a list of Dto objects
     *
     * @param areas The areas
     * @return The Dto objects
     */
    public static List<AreaDto> toDto(Set<Area> areas) {
        if (areas == null) {
            throw new IllegalStateException("Area list is null");
        }
        List<AreaDto> result = new ArrayList<>();
        List<RegionDto> regions = new ArrayList<>();

        areas.forEach(area -> {
            result.add(toDto(area));
            Region region = area.getRegions().iterator().next();
            regions.add(RegionConverter.toDto(region));
        });

        result.sort(new AreaDtoSorter(regions));
        return result;
    }

    /**
     * Convert entity to dto
     *
     * @param a An area
     * @return dto object
     */
    public static AreaDto toDto(Area a) {
        if (a == null) {
            throw new IllegalStateException("Area is null");
        }
        return new AreaDto(a);
    }

    /**
     * Create an Area entity
     *
     * @param a  An area
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
