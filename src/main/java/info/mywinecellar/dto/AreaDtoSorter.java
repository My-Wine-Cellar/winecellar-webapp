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
public class AreaDtoSorter implements Comparator<AreaDto> {

    /**
     * Default constructor
     */
    public AreaDtoSorter() {
    }

    /**
     * Compare two countries
     * @param a1 The first area
     * @param a2 The second area
     * @return 0 if equals, otherwise based on name
     */
    public int compare(AreaDto a1, AreaDto a2) {
        return a1.getName().compareTo(a2.getName());
    }
}

