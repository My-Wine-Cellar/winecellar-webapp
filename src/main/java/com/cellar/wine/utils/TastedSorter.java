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

    public TastedSorter() {
        this(false);
    }

    public TastedSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    public int compare(Tasted t1, Tasted t2) {
        if (t1.getId().equals(t2.getId()))
            return 0;

        return wineSorter.compare(t1.getWine(), t2.getWine());
    }
}

