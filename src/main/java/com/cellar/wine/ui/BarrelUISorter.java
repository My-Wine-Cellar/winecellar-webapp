package com.cellar.wine.ui;

import java.util.Comparator;

/**
 * Sorter for BarrelUI beans
 */
public class BarrelUISorter implements Comparator<BarrelUI> {

    public BarrelUISorter() {
    }

    public int compare(BarrelUI b1, BarrelUI b2) {
        int result;

        result = b1.getPercentage().compareTo(b2.getPercentage());
        if (result != 0)
            return -result;

        result = b1.getAging().compareTo(b2.getAging());
        if (result != 0)
            return -result;

        result = b1.getSize().compareTo(b2.getSize());
        if (result != 0)
            return result;

        return b1.getName().compareTo(b2.getName());
    }
}

