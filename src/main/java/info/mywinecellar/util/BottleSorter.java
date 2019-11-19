/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.util;

import info.mywinecellar.model.Bottle;

import java.util.Comparator;

/**
 * Sorter for Bottle entities
 */
public class BottleSorter implements Comparator<Bottle> {
    private WineSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public BottleSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public BottleSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    /**
     * Compare two bottle entities
     * @param b1 Instance one
     * @param b2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(Bottle b1, Bottle b2) {
        int result;

        if (b1.getId().equals(b2.getId())) {
            return 0;
        }

        return wineSorter.compare(b1.getWine(), b2.getWine());
    }
}

