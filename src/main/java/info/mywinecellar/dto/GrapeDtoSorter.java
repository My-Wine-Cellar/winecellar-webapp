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
public class GrapeDtoSorter implements Comparator<GrapeDto> {

    /**
     * Default constructor
     */
    public GrapeDtoSorter() {
    }

    /**
     * Compare two grapes
     * @param g1 The first grape
     * @param g2 The second grape
     * @return 0 if equals, otherwise based on percentage and name
     */
    public int compare(GrapeDto g1, GrapeDto g2) {
        int result;

        if (g1.getPercentage() != null && g2.getPercentage() != null) {
            result = g1.getPercentage().compareTo(g2.getPercentage());
            if (result != 0) {
                return -result;
            }
        }

        return g1.getName().compareTo(g2.getName());
    }
}

