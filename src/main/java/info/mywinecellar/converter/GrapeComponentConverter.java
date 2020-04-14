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

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * GrapeComponent converter
 */
@Component
public class GrapeComponentConverter {

    /**
     * Convert entity to dto
     *
     * @param grapeComponent GrapeComponent
     * @return dto
     */
    public GrapeComponentDto toDto(GrapeComponent grapeComponent) {
        if (grapeComponent == null) {
            throw new IllegalStateException("GrapeComponent is null");
        }
        return new GrapeComponentDto(grapeComponent);
    }

    /**
     * Create a GrapeComponent entity
     *
     * @param entity GrapeComponent
     * @param dto    GrapeComponentDto
     * @return entity
     */
    public GrapeComponent toEntity(GrapeComponent entity, GrapeComponentDto dto) {
        Grape grape = new Grape();
        grape.setId(dto.getGrapeId());

        Wine wine = new Wine();
        wine.setId(dto.getWineId());

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
    public List<GrapeComponent> toEntity(List<GrapeComponentDto> dtoList) {
        List<GrapeComponent> list = new ArrayList<>();
        dtoList.forEach(dto -> list.add(toEntity(null, dto)));
        return list;
    }


}
