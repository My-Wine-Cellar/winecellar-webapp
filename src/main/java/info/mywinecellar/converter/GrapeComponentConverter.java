/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.GrapeComponentDto;
import info.mywinecellar.model.Fermentation;
import info.mywinecellar.model.Grape;
import info.mywinecellar.model.GrapeComponent;
import info.mywinecellar.model.Maceration;
import info.mywinecellar.model.Wine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for {@link GrapeComponent} and {@link GrapeComponentDto} conversion
 */
public final class GrapeComponentConverter {

    private GrapeComponentConverter() {
    }

    /**
     * Convert entity to dto
     *
     * @param grapeComponent GrapeComponent
     * @return dto
     */
    public static GrapeComponentDto toDto(GrapeComponent grapeComponent) {
        return Optional.ofNullable(grapeComponent)
                .map(GrapeComponentDto::new)
                .orElse(null);
    }

    /**
     * Create a GrapeComponent entity
     *
     * @param entity GrapeComponent
     * @param dto    GrapeComponentDto
     * @return entity
     */
    public static GrapeComponent toEntity(GrapeComponent entity, GrapeComponentDto dto) {
        Grape grape = new Grape();
        grape.setId(dto.getGrapeId());

        Wine wine = Wine.createWineById(dto.getWineId());

        Fermentation fermentation = new Fermentation();
        fermentation.setId(dto.getFermentationId());

        Maceration maceration = new Maceration();
        maceration.setId(dto.getMacerationId());

        if (entity == null) {
            entity = new GrapeComponent(dto.getPercentage(), dto.getHarvestBegin(), dto.getHarvestEnd(),
                    grape, wine);
            if (dto.getFermentationId() == null) {
                entity.setFermentation(null);
            } else {
                entity.setFermentation(fermentation);
            }
            if (dto.getMacerationId() == null) {
                entity.setMaceration(null);
            } else {
                entity.setMaceration(maceration);
            }
        } else {
            entity.setPercentage(dto.getPercentage());
            entity.setHarvestBegin(dto.getHarvestBegin());
            entity.setHarvestEnd(dto.getHarvestEnd());
            entity.setGrape(grape);
            entity.setWine(wine);
            entity.setFermentation(fermentation);
            entity.setMaceration(maceration);
        }
        return entity;
    }

    /**
     * Create a GrapeComponent entity list
     *
     * @param dtoList GrapeComponentDto list
     * @return entity list
     */
    public static List<GrapeComponent> toEntity(List<GrapeComponentDto> dtoList) {
        return Stream.ofNullable(dtoList)
                .flatMap(Collection::stream)
                .map(dto -> GrapeComponentConverter.toEntity(null, dto))
                .collect(Collectors.toList());
    }


}
