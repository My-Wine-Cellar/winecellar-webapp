/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.utils;

import com.cellar.wine.models.Tasted;

import java.util.Comparator;

/**
 * Sorter for Tasted entities
 */
public class TastedSorter implements Comparator<Tasted> {
    private WineSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public TastedSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public TastedSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    /**
     * Compare two tasted entities
     * @param t1 Instance one
     * @param t2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(Tasted t1, Tasted t2) {
        if (t1.getId().equals(t2.getId())) {
            return 0;
        }

        return wineSorter.compare(t1.getWine(), t2.getWine());
    }
}

