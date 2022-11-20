/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ClosureDto;
import info.mywinecellar.model.Closure;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Optional.ofNullable(closure)
                .map(ClosureDto::new)
                .orElse(null);
    }

    /**
     * Convert Entity list to Dto list
     *
     * @param closures List of Closures
     * @return ClosureDto list
     */
    public static List<ClosureDto> toDto(Set<Closure> closures) {
        return Stream.ofNullable(closures)
                .flatMap(Collection::stream)
                .map(ClosureConverter::toDto)
                .collect(Collectors.toList());
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
