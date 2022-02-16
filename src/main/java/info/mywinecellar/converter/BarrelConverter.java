/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.BarrelDto;
import info.mywinecellar.model.Barrel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        if (barrel == null) {
            throw new IllegalStateException("Barrel is null");
        }
        return new BarrelDto(barrel);
    }

    /**
     * Convert entity list to dto list
     *
     * @param barrels Barrel list
     * @return entity list
     */
    public static List<BarrelDto> toDto(Set<Barrel> barrels) {
        if (barrels == null) {
            throw new IllegalStateException("Barrel list is null");
        }
        List<BarrelDto> result = new ArrayList<>();
        barrels.forEach(dto -> result.add(toDto(dto)));
        /* SORTING */
        return result;
    }
}
