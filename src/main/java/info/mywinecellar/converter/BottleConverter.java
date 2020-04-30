/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.BottleDto;
import info.mywinecellar.model.Bottle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class BottleConverter {

    /**
     * Convert Bottle to BottleDto
     *
     * @param bottle bottle
     * @return BottleDto
     */
    public BottleDto toDto(Bottle bottle) {
        if (bottle == null) {
            throw new IllegalStateException("Bottle is null");
        }
        return new BottleDto(bottle);
    }

    /**
     * Convert Bottle list to BottleDto list
     *
     * @param bottles List of bottles
     * @return List of BottleDto's
     */
    public List<BottleDto> toDto(Set<Bottle> bottles) {
        if (bottles == null) {
            throw new IllegalStateException("Bottle list is null");
        }
        List<BottleDto> result = new ArrayList<>();
        bottles.forEach(bottle -> result.add(toDto(bottle)));
        /* SORTING */
        return result;
    }
}
