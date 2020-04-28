/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import java.util.Comparator;

public class GrapeComponentSorter implements Comparator<GrapeComponentDto> {

    /**
     * Default constructor
     */
    public GrapeComponentSorter() {
    }

    /**
     * Compare GrapeComponents
     *
     * @param gc1 GrapeComponentDto
     * @param gc2 GrapeComponentDto
     * @return int
     */
    public int compare(GrapeComponentDto gc1, GrapeComponentDto gc2) {
        int result;

        if (gc1.getPercentage() != null && gc2.getPercentage() != null) {
            result = gc1.getPercentage().compareTo(gc2.getPercentage());
            if (result != 0) {
                return -result;
            }
        }
        return gc1.getName().compareTo(gc2.getName());
    }
}
