/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.util;

import info.mywinecellar.model.Wishlist;

import java.util.Comparator;

/**
 * Sorter for Wishlist entities
 */
public class WishlistSorter implements Comparator<Wishlist> {
    private WineSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public WishlistSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public WishlistSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    /**
     * Compare two wishlist entities
     * @param w1 Instance one
     * @param w2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(Wishlist w1, Wishlist w2) {
        if (w1.getId().equals(w2.getId())) {
            return 0;
        }

        return wineSorter.compare(w1.getWine(), w2.getWine());
    }
}

