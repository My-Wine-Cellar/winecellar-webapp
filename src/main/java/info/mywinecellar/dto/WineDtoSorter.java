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
public class WineDtoSorter implements Comparator<WineDto> {

    /**
     * Default constructor
     */
    public WineDtoSorter() {
    }

    /**
     * Compare two wines
     * @param w1 The first wine
     * @param w2 The second wine
     * @return 0 if equals, otherwise based on name and vintage
     */
    public int compare(WineDto w1, WineDto w2) {
        int result;

        result = w1.getName().compareTo(w2.getName());

        if (result == 0) {
            result = w1.getVintage().compareTo(w2.getVintage());
            if (result != 0) {
                return -result;
            }
        }

        return result;
    }
}

