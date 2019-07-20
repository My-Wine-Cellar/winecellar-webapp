package com.cellar.wine.utils;

import java.util.Comparator;

/**
 * Sorter for WineGrape beans
 */
public class WineGrapeSorter implements Comparator<WineGrape> {

    public WineGrapeSorter() {
    }

    public int compare(WineGrape wg1, WineGrape wg2) {
        int result;

        result = wg1.getPercentage().compareTo(wg2.getPercentage());
        if (result != 0)
            return -result;

        return wg1.getName().compareTo(wg2.getName());
    }
}

