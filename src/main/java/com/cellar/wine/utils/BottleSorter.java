package com.cellar.wine.utils;

import com.cellar.wine.models.Bottle;

import java.util.Comparator;

/**
 * Sorter for Bottle entities
 */
public class BottleSorter implements Comparator<Bottle> {
    private WineSorter wineSorter;

    public BottleSorter() {
        this(false);
    }

    public BottleSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    public int compare(Bottle b1, Bottle b2) {
        int result;
        
        if (b1.getId().equals(b2.getId()))
            return 0;

        return wineSorter.compare(b1.getWine(), b2.getWine());
    }
}

