/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import java.util.Comparator;

/**
 * Sorter for Bottle DTOs
 */
public class BottleDtoSorter implements Comparator<BottleDto> {
    private WineDtoSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public BottleDtoSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public BottleDtoSorter(boolean country) {
        wineSorter = new WineDtoSorter(country);
    }

    /**
     * Compare two bottle entities
     * @param b1 Instance one
     * @param b2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(BottleDto b1, BottleDto b2) {
        if (b1.getId().equals(b2.getId())) {
            return 0;
        }

        return wineSorter.compare(b1.getWine(), b2.getWine());
    }
}

