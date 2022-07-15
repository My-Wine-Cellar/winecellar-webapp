/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Region;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Region} and {@link RegionDto} conversion
 */
public final class RegionConverter {

    private RegionConverter() {
    }

    /**
     * Convert Region entity to RegionDto
     *
     * @param region Region region
     * @return RegionDto object
     */
    public static RegionDto toDto(Region region) {
        return Optional.ofNullable(region)
                .map(RegionDto::new)
                .orElse(null);
    }

    /**
     * Convert a list of Regions to a list of RegionDto objects
     *
     * @param regions List<Region> regions
     * @return List<RegionDto> regionDto's
     */
    public static List<RegionDto> toDto(Set<Region> regions) {
        return regions.stream()
                .map(RegionConverter::toDto)
                .sorted(Comparator.comparing(RegionDto::getName))
                .collect(Collectors.toList());
    }

    /**
     * Update and convert a Region entity from RegionDto to prepare for save
     *
     * @param entity Region entity
     * @param dto    RegionDto dto
     * @return Region entity
     */
    public static Region toEntity(Region entity, RegionDto dto) {
        if (entity == null) {
            entity = new Region(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            entity.setDescription(dto.getDescription());
            entity.setWeblink(dto.getWeblink());
        }
        return entity;
    }
}
