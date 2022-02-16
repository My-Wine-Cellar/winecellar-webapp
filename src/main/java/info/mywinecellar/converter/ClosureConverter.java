/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ClosureDto;
import info.mywinecellar.model.Closure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link Closure} and {@link ClosureDto} conversion
 */
public final class ClosureConverter {

    private ClosureConverter() {
    }

    /**
     * Convert Entity to Dto
     *
     * @param closure Closure
     * @return Dto
     */
    public static ClosureDto toDto(Closure closure) {
        if (closure == null) {
            throw new IllegalStateException("Closure is null");
        }
        return new ClosureDto(closure);
    }

    /**
     * Convert Entity list to Dto list
     *
     * @param closures List of Closures
     * @return ClosureDto list
     */
    public static List<ClosureDto> toDto(Set<Closure> closures) {
        if (closures == null) {
            throw new IllegalStateException("Closure list is null");
        }
        List<ClosureDto> result = new ArrayList<>();
        closures.forEach(closure -> result.add(toDto(closure)));
        /* SORTING */
        return result;
    }

    /**
     * Create a Closure entity
     *
     * @param closure Closure closure
     * @param dto     ClosureDto dto
     * @return Closure entity
     */
    public static Closure toEntity(Closure closure, ClosureDto dto) {
        if (closure == null) {
            closure = new Closure(dto.getName(), dto.getDescription(), dto.getWeblink());
        } else {
            closure.setDescription(dto.getDescription());
            closure.setWeblink(dto.getWeblink());
        }
        return closure;
    }
}
