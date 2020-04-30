/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ShapeDto;
import info.mywinecellar.model.Shape;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ShapeConverter {

    /**
     * Convert Entity to Dto
     *
     * @param shape Shape
     * @return Dto
     */
    public ShapeDto toDto(Shape shape) {
        if (shape == null) {
            throw new IllegalStateException("Shape is null");
        }
        return new ShapeDto(shape);
    }

    /**
     * Convert Entity List to Dto list
     *
     * @param shapes List of Shapes
     * @return ShapeDto list
     */
    public List<ShapeDto> toDto(List<Shape> shapes) {
        if (shapes == null) {
            throw new IllegalStateException("Shape list is null");
        }
        List<ShapeDto> result = new ArrayList<>();
        shapes.forEach(shape -> result.add(toDto(shape)));
        /* SORTING */
        return result;
    }

    /**
     * Create a Shape entity
     *
     * @param shape Shape shape
     * @param dto   ShapeDto dto
     * @return Shape entity
     */
    public Shape toEntity(Shape shape, ShapeDto dto) {
        if (shape == null) {
            shape = new Shape(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            shape.setDescription(dto.getDescription());
            shape.setWeblink(dto.getWeblink());
        }
        return shape;
    }
}
