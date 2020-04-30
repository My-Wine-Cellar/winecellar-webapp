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
 * Sorter for Wishlist DTOs
 */
public class WishlistDtoSorter implements Comparator<WishlistDto> {
    private WineDtoSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public WishlistDtoSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public WishlistDtoSorter(boolean country) {
        wineSorter = new WineDtoSorter(country);
    }

    /**
     * Compare two wishlist entities
     * @param w1 Instance one
     * @param w2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(WishlistDto w1, WishlistDto w2) {
        if (w1.getId().equals(w2.getId())) {
            return 0;
        }

        return wineSorter.compare(w1.getWine(), w2.getWine());
    }
}

