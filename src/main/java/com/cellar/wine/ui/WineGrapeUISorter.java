package com.cellar.wine.ui;

import java.util.Comparator;

/**
 * Sorter for WineGrapeUI beans
 */
public class WineGrapeUISorter implements Comparator<WineGrapeUI> {

    public WineGrapeUISorter() {
    }

    public int compare(WineGrapeUI wg1, WineGrapeUI wg2) {
        int result;

        result = wg1.getPercentage().compareTo(wg2.getPercentage());
        if (result != 0)
            return -result;

        return wg1.getName().compareTo(wg2.getName());
    }
}

