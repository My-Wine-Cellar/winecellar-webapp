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
 * Sorter for Tasted DTOs
 */
public class TastedDtoSorter implements Comparator<TastedDto> {
    private WineDtoSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public TastedDtoSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public TastedDtoSorter(boolean country) {
        wineSorter = new WineDtoSorter(country);
    }

    /**
     * Compare two tasted entities
     * @param t1 Instance one
     * @param t2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(TastedDto t1, TastedDto t2) {
        if (t1.getId().equals(t2.getId())) {
            return 0;
        }

        return wineSorter.compare(t1.getWine(), t2.getWine());
    }
}

