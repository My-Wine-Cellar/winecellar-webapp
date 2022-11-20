/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ShapeDto;
import info.mywinecellar.model.Shape;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Shape} and {@link ShapeDto} conversion
 */
public final class ShapeConverter {

    private ShapeConverter() {
    }

    /**
     * Convert Entity to Dto
     *
     * @param shape Shape
     * @return Dto
     */
    public static ShapeDto toDto(Shape shape) {
        return Optional.ofNullable(shape)
                .map(ShapeDto::new)
                .orElse(null);
    }

    /**
     * Convert Entity List to Dto list
     *
     * @param shapes List of Shapes
     * @return ShapeDto list
     */
    public static List<ShapeDto> toDto(Set<Shape> shapes) {
        return shapes.stream()
                .map(ShapeConverter::toDto)
                //.sorted()
                .collect(Collectors.toList());
    }

    /**
     * Create a Shape entity
     *
     * @param shape Shape shape
     * @param dto   ShapeDto dto
     * @return Shape entity
     */
    public static Shape toEntity(Shape shape, ShapeDto dto) {
        if (shape == null) {
            shape = new Shape(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            shape.setDescription(dto.getDescription());
            shape.setWeblink(dto.getWeblink());
        }
        return shape;
    }
}
