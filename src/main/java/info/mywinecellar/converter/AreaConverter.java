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

import javax.inject.Inject;

import org.springframework.stereotype.Component;

/**
 * Area converter
 */
@Component
public class AreaConverter {

    @Inject
    private RegionConverter regionConverter;

    /**
     * Create a list of Dto objects
     *
     * @param areas The areas
     * @return The Dto objects
     */
    public List<AreaDto> toDto(Set<Area> areas) {
        if (areas == null) {
            throw new IllegalStateException("Area list is null");
        }
        List<AreaDto> result = new ArrayList<>();
        List<RegionDto> regions = new ArrayList<>();

        for (Area a : areas) {
            result.add(toDto(a));

            Region r = a.getRegions().iterator().next();
            regions.add(regionConverter.toDto(r));
        }

        result.sort(new AreaDtoSorter(regions));
        return result;
    }

    /**
     * Convert entity to dto
     *
     * @param a An area
     * @return dto object
     */
    public AreaDto toDto(Area a) {
        if (a == null) {
            throw new IllegalStateException("Area is null");
        }
        return new AreaDto(a);
    }

    /**
     * Create an Area entity
     *
     * @param a  An area
     * @param ui The Dto object
     * @return The area
     */
    public Area toEntity(Area a, AreaDto ui) {
        if (a == null) {
            a = new Area(ui.getName(), ui.getDescription(), ui.getWeblink());
        } else {
            a.setDescription(ui.getDescription());
            a.setWeblink(ui.getWeblink());
        }
        return a;
    }
}
