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
 * Sorter for Review DTOs
 */
public class ReviewDtoSorter implements Comparator<ReviewDto> {
    private WineDtoSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public ReviewDtoSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public ReviewDtoSorter(boolean country) {
        wineSorter = new WineDtoSorter(country);
    }

    /**
     * Compare two review entities
     * @param r1 Instance one
     * @param r2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(ReviewDto r1, ReviewDto r2) {
        int result;

        if (r1.getId().equals(r2.getId())) {
            return 0;
        }

        result = r1.getStars().compareTo(r2.getStars());
        if (result != 0) {
            return -result;
        }

        return wineSorter.compare(r1.getWine(), r2.getWine());
    }
}

