/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.WineDetailsDto;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.model.Closure;
import info.mywinecellar.model.Color;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Shape;
import info.mywinecellar.model.Type;
import info.mywinecellar.model.Wine;
import info.mywinecellar.util.Image;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return Optional.ofNullable(wine)
                .map(WineDto::new)
                .orElse(null);
    }

    /**
     * Convert entity to dto
     *
     * @param wine wine
     * @return dto object
     */
    public static WineDetailsDto toDetailsDto(Wine wine) {
        return Optional.ofNullable(wine)
                .map(WineDetailsDto::new)
                .orElse(null);
    }

    /**
     * Convert entity list to dto list
     *
     * @param wines wines
     * @return dto list
     */
    public static List<WineDto> toDto(Set<Wine> wines) {
        return wines.stream()
                .map(WineConverter::toDto)
                .sorted(Comparator.comparing(WineDto::getName)
                        .thenComparing(WineDto::getVintage)
                        .thenComparing(WineDto::getSize))
                .collect(Collectors.toList());
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
