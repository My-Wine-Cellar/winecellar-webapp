/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.WineDetailsDto;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.dto.WineDtoSorter;
import info.mywinecellar.model.Closure;
import info.mywinecellar.model.Color;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Shape;
import info.mywinecellar.model.Type;
import info.mywinecellar.model.Wine;
import info.mywinecellar.util.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link Wine}, {@link WineDetailsDto}, and {@link WineDto} conversion
 */
public final class WineConverter {

    private WineConverter() {
    }

    /**
     * Convert entity to dto
     *
     * @param wine wine
     * @return dto object
     */
    public static WineDto toDto(Wine wine) {
        if (wine == null) {
            throw new IllegalStateException("Wine is null");
        }
        return new WineDto(wine);
    }

    /**
     * Convert entity to dto
     *
     * @param wine wine
     * @return dto object
     */
    public static WineDetailsDto toDetailsDto(Wine wine) {
        if (wine == null) {
            throw new IllegalStateException("Wine is null");
        }
        return new WineDetailsDto(wine);
    }

    /**
     * Convert entity list to dto list
     *
     * @param wines wines
     * @return dto list
     */
    public static List<WineDto> toDto(Set<Wine> wines) {
        if (wines == null) {
            throw new IllegalStateException("Wine list is null");
        }
        List<WineDto> result = new ArrayList<>();
        wines.forEach(wine -> result.add(toDto(wine)));
        result.sort(new WineDtoSorter());
        return result;
    }

    /**
     * Create a Wine entity
     *
     * @param entity entity
     * @param dto    dto
     * @return entity
     */
    public static Wine toEntity(Wine entity, WineDto dto) {
        Producer producer = new Producer();
        producer.setId(dto.getProducerId());

        Color color = new Color();
        color.setId(dto.getColorId());
        Type type = new Type();
        type.setId(dto.getTypeId());
        Shape shape = new Shape();
        shape.setId(dto.getShapeId());
        Closure closure = new Closure();
        closure.setId(dto.getClosureId());

        if (entity == null) {
            entity = new Wine.Builder(dto.getName(), dto.getVintage(), dto.getSize())
                    .alcohol(dto.getAlcohol()).acid(dto.getAcid()).pH(dto.getPH()).bottleAging(dto.getBottleAging())
                    .description(dto.getDescription()).weblink(dto.getWeblink()).image(Image.decode(dto.getImage()))
                    .producer(producer).closure(closure).color(color).type(type).shape(shape)
                    .build();
        } else {
            entity.setName(dto.getName());
            entity.setVintage(dto.getVintage());
            entity.setSize(dto.getSize());
            entity.setAlcohol(dto.getAlcohol());
            entity.setAcid(dto.getAcid());
            entity.setPH(dto.getPH());
            entity.setBottleAging(dto.getBottleAging());
            entity.setDescription(dto.getDescription());
            entity.setWeblink(dto.getWeblink());
            entity.setImage(Image.decode(dto.getImage()));
        }
        return entity;
    }
}
