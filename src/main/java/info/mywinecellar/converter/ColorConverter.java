/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ColorDto;
import info.mywinecellar.model.Color;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for {@link Color} and {@link ColorDto} conversion
 */
public final class ColorConverter {

    private ColorConverter() {
    }

    /**
     * Convert Entity to Dto
     *
     * @param color Color
     * @return Dto
     */
    public static ColorDto toDto(Color color) {
        return Optional.ofNullable(color)
                .map(ColorDto::new)
                .orElse(null);
    }

    /**
     * Convert Entity list to Dto list
     *
     * @param colors List of Colors
     * @return ColorDto list
     */
    public static List<ColorDto> toDto(Set<Color> colors) {
        return Stream.ofNullable(colors)
                .flatMap(Collection::stream)
                .map(ColorConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Create a Color entity
     *
     * @param entity Color entity
     * @param dto    ColorDto dto
     * @return Color entity
     */
    public static Color toEntity(Color entity, ColorDto dto) {
        if (entity == null) {
            entity = new Color(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            entity.setDescription(dto.getDescription());
            entity.setWeblink(dto.getWeblink());
        }
        return entity;
    }
}
