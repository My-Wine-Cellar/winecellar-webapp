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

import org.springframework.stereotype.Component;

@Component
public class BarrelConverter {

    /**
     * Convert Barrel entity to BarrelDto
     *
     * @param barrel Barrel barrel
     * @return BarrelDto object
     */
    public BarrelDto toDto(Barrel barrel) {
        if (barrel == null) {
            throw new IllegalStateException("Barrel is null");
        }
        return new BarrelDto(barrel);
    }
}
