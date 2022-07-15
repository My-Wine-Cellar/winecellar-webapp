/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.TypeDto;
import info.mywinecellar.model.Type;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Type} and {@link TypeDto} conversion
 */
public final class TypeConverter {

    private TypeConverter() {
    }

    /**
     * Convert Entity to Dto
     *
     * @param type Type
     * @return Dto
     */
    public static TypeDto toDto(Type type) {
        return Optional.ofNullable(type)
                .map(TypeDto::new)
                .orElse(null);
    }

    /**
     * Convert Entity list to Dto list
     *
     * @param types List of Types
     * @return TypeDto list
     */
    public static List<TypeDto> toDto(Set<Type> types) {
        return types.stream()
                .map(TypeConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Create a Type entity
     *
     * @param type Type type
     * @param dto  TypeDto dto
     * @return Type entity
     */
    public static Type toEntity(Type type, TypeDto dto) {
        if (type == null) {
            type = new Type(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            type.setDescription(dto.getDescription());
            type.setWeblink(dto.getWeblink());
        }
        return type;
    }
}
