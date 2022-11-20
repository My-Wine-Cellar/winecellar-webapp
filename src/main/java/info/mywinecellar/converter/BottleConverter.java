/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.BottleDto;
import info.mywinecellar.model.Bottle;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Bottle} and {@link BottleDto} conversion
 */
public final class BottleConverter {

    private BottleConverter() {
    }

    /**
     * Convert Bottle to BottleDto
     *
     * @param bottle bottle
     * @return BottleDto
     */
    public static BottleDto toDto(Bottle bottle) {
        return Optional.ofNullable(bottle)
                .map(BottleDto::new)
                .orElse(null);
    }

    /**
     * Convert Bottle list to BottleDto list
     *
     * @param bottles List of bottles
     * @return List of BottleDto's
     */
    public static List<BottleDto> toDto(Set<Bottle> bottles) {
        return bottles.stream()
                .map(BottleConverter::toDto)
                .collect(Collectors.toList());
    }
}
