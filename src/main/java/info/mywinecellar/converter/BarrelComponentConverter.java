/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.dto.BarrelDtoSorter;
import info.mywinecellar.model.Barrel;
import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.GrapeComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class BarrelComponentConverter {

    /**
     * Convert entity to dto
     *
     * @param bc BarrelComponent
     * @return BarrelDto
     */
    public BarrelDto toDto(BarrelComponent bc) {
        if (bc == null) {
            throw new IllegalStateException("BarrelComponent is null");
        }
        return new BarrelDto(bc);
    }

    /**
     * Convert entity list to dto list
     *
     * @param barrels BarrelComponent list
     * @return BarrelDto list
     */
    public List<BarrelDto> toDto(Set<BarrelComponent> barrels) {
        if (barrels == null) {
            throw new IllegalStateException("Barrel list is null");
        }
        List<BarrelDto> result = new ArrayList<>();
        barrels.forEach(barrel -> result.add(toDto(barrel)));
        result.sort(new BarrelDtoSorter());
        return result;
    }

    /**
     * Create a BarrelComponent entity
     *
     * @param entity BarrelComponent
     * @param dto    BarrelDto
     * @return entity
     */
    public BarrelComponent toEntity(BarrelComponent entity, BarrelDto dto) {
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
    public List<BarrelComponent> toEntity(List<BarrelDto> dtoList) {
        List<BarrelComponent> list = new ArrayList<>();
        dtoList.forEach(dto -> list.add(toEntity(null, dto)));
        return list;
    }
}
