/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.model.Barrel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Barrel} and {@link BarrelDto} conversion
 */
public final class BarrelConverter {

    private BarrelConverter() {
    }

    /**
     * Convert Barrel entity to BarrelDto
     *
     * @param barrel Barrel barrel
     * @return BarrelDto object
     */
    public static BarrelDto toDto(Barrel barrel) {
        return Optional.ofNullable(barrel)
                .map(BarrelDto::new)
                .orElse(null);
    }

    /**
     * Convert entity list to dto list
     *
     * @param barrels Barrel list
     * @return entity list
     */
    public static List<BarrelDto> toDto(Set<Barrel> barrels) {
        return barrels.stream()
                .map(BarrelConverter::toDto)
                .sorted(Comparator.comparing(BarrelDto::getPercentage)
                        .thenComparing(BarrelDto::getAging)
                        .thenComparing(BarrelDto::getSize)
                        .thenComparing(BarrelDto::getName))
                .collect(Collectors.toList());
    }
}
