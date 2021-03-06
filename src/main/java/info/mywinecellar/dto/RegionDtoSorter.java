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
public class RegionDtoSorter implements Comparator<RegionDto> {

    /**
     * Default constructor
     */
    public RegionDtoSorter() {
    }

    /**
     * Compare two countries
     * @param r1 The first region
     * @param r2 The second region
     * @return 0 if equals, otherwise based on name
     */
    public int compare(RegionDto r1, RegionDto r2) {
        return r1.getName().compareTo(r2.getName());
    }
}

