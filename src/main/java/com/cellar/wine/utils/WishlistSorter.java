/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.utils;

import com.cellar.wine.models.Wishlist;

import java.util.Comparator;

/**
 * Sorter for Wishlist entities
 */
public class WishlistSorter implements Comparator<Wishlist> {
    private WineSorter wineSorter;

    public WishlistSorter() {
        this(false);
    }

    public WishlistSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    public int compare(Wishlist w1, Wishlist w2) {
        if (w1.getId().equals(w2.getId()))
            return 0;

        return wineSorter.compare(w1.getWine(), w2.getWine());
    }
}

