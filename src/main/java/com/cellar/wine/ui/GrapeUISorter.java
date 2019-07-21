package com.cellar.wine.ui;

import java.util.Comparator;

/**
 * Sorter for GrapeUI beans
 */
public class GrapeUISorter implements Comparator<GrapeUI> {

    public GrapeUISorter() {
    }

    public int compare(GrapeUI wg1, GrapeUI wg2) {
        int result;

        result = wg1.getPercentage().compareTo(wg2.getPercentage());
        if (result != 0)
            return -result;

        return wg1.getName().compareTo(wg2.getName());
    }
}

