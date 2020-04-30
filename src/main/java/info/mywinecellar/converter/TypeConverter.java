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

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TypeConverter {

    /**
     * Convert Entity to Dto
     *
     * @param type Type
     * @return Dto
     */
    public TypeDto toDto(Type type) {
        if (type == null) {
            throw new IllegalStateException("Type is null");
        }
        return new TypeDto(type);
    }

    /**
     * Convert Entity list to Dto list
     *
     * @param types List of Types
     * @return TypeDto list
     */
    public List<TypeDto> toDto(List<Type> types) {
        if (types == null) {
            throw new IllegalStateException("Type list is null");
        }
        List<TypeDto> result = new ArrayList<>();
        types.forEach(type -> result.add(toDto(type)));
        /* SORTING */
        return result;
    }

    /**
     * Create a Type entity
     *
     * @param type Type type
     * @param dto  TypeDto dto
     * @return Type entity
     */
    public Type toEntity(Type type, TypeDto dto) {
        if (type == null) {
            type = new Type(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            type.setDescription(dto.getDescription());
            type.setWeblink(dto.getWeblink());
        }
        return type;
    }
}
