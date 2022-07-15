/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.model.Barrel;
import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.GrapeComponent;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for {@link BarrelComponent} and {@link BarrelDto} conversion
 */
public final class BarrelComponentConverter {

    private BarrelComponentConverter() {
    }

    /**
     * Convert entity to dto
     *
     * @param bc BarrelComponent
     * @return BarrelDto
     */
    public static BarrelDto toDto(BarrelComponent bc) {
        return Optional.ofNullable(bc)
                .map(BarrelDto::new)
                .orElse(null);
    }

    /**
     * Convert entity list to dto list
     *
     * @param barrels BarrelComponent list
     * @return BarrelDto list
     */
    public static List<BarrelDto> toDto(Set<BarrelComponent> barrels) {
        return Stream.ofNullable(barrels)
                .flatMap(Collection::stream)
                .map(BarrelComponentConverter::toDto)
                .sorted(Comparator.comparing(BarrelDto::getPercentage)
                        .thenComparing(BarrelDto::getAging)
                        .thenComparing(BarrelDto::getSize)
                        .thenComparing(BarrelDto::getName))
                .collect(Collectors.toList());
    }

    /**
     * Create a BarrelComponent entity
     *
     * @param entity BarrelComponent
     * @param dto    BarrelDto
     * @return entity
     */
    public static BarrelComponent toEntity(BarrelComponent entity, BarrelDto dto) {
        Barrel barrel = new Barrel();
        barrel.setId(dto.getBarrelId());

        GrapeComponent grapeComponent = new GrapeComponent();
        grapeComponent.setId(dto.getGrapeComponentId());

        if (entity == null) {
            entity = new BarrelComponent(dto.getPercentage(), dto.getSize(), dto.getAging(), grapeComponent, barrel);
        } else {
            entity.setPercentage(dto.getPercentage());
            entity.setSize(dto.getSize());
            entity.setAging(dto.getAging());
            entity.setGrapeComponent(grapeComponent);
            entity.setBarrel(barrel);
        }
        return entity;
    }

    /**
     * Create a BarrelComponent entity list
     *
     * @param dtoList BarrelDto list
     * @return entity list
     */
    public static List<BarrelComponent> toEntity(List<BarrelDto> dtoList) {
        return Stream.ofNullable(dtoList)
                .flatMap(Collection::stream)
                .map(dto -> BarrelComponentConverter.toEntity(null, dto))
                .collect(Collectors.toList());
    }
}
