/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.WineDto;
import info.mywinecellar.dto.WineDtoSorter;
import info.mywinecellar.model.Wine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WineConverter {

    /**
     * Convert entity to dto
     *
     * @param wine wine
     * @return dto object
     */
    public WineDto toDto(Wine wine) {
        if (wine == null) {
            throw new IllegalStateException("Wine is null");
        }
        return new WineDto(wine);
    }

    /**
     * Convert entity list to dto list
     *
     * @param wines wines
     * @return dto list
     */
    public List<WineDto> toDto(List<Wine> wines) {
        if (wines == null) {
            throw new IllegalStateException("Wine list is null");
        }
        List<WineDto> result = new ArrayList<>();
        wines.forEach(wine -> result.add(toDto(wine)));
        result.sort(new WineDtoSorter());
        return result;
    }

    /**
     * Convert dto to entity
     *
     * @param entity entity
     * @param dto    dto
     * @return entity
     */
    public Wine toEntity(Wine entity, WineDto dto) {
        // TODO go back to 'main' Wine constructor, should fix new and edit
        if (entity == null) {
            entity = new Wine(dto.getName(), dto.getVintage(), dto.getSize());
        } else {
            entity.setName(dto.getName());
            entity.setVintage(dto.getVintage());
            entity.setSize(dto.getSize());
        }
        return entity;
    }
}
