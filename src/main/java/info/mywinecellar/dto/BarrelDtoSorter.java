/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import java.util.Comparator;

/**
 * Sorter for dto beans
 */
public class BarrelDtoSorter implements Comparator<BarrelDto> {

    /**
     * Default constructor
     */
    public BarrelDtoSorter() {
    }

    /**
     * Compare two barrels
     * @param b1 The first barrel
     * @param b2 The second barrel
     * @return 0, if equals; otherwise based on percentage, aging, size and name
     */
    public int compare(BarrelDto b1, BarrelDto b2) {
        int result;

        result = b1.getPercentage().compareTo(b2.getPercentage());
        if (result != 0) {
            return -result;
        }

        result = b1.getAging().compareTo(b2.getAging());
        if (result != 0) {
            return -result;
        }

        result = b1.getSize().compareTo(b2.getSize());
        if (result != 0) {
            return result;
        }

        return b1.getName().compareTo(b2.getName());
    }
}

