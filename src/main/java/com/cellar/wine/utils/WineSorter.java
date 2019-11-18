/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.utils;

import com.cellar.wine.models.Area;
import com.cellar.wine.models.Country;
import com.cellar.wine.models.Region;
import com.cellar.wine.models.Wine;

import java.util.Comparator;

/**
 * Sorter for Wine entities
 */
public class WineSorter implements Comparator<Wine> {
    private boolean country;

    /**
     * Default constructor that doesn't take country into account
     */
    public WineSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public WineSorter(boolean country) {
        this.country = country;
    }

    /**
     * Compare two wine entities
     * @param w1 Instance one
     * @param w2 Instance two
     * @return 0 if equals, otherwise result of the property priorities
     */
    public int compare(Wine w1, Wine w2) {
        int result;

        if (w1.getId().equals(w2.getId())) {
            return 0;
        }

        if (country) {
            Area a1 = w1.getProducer().getAreas().get(0);
            Region r1 = a1.getRegions().get(0);
            Country c1 = r1.getCountry();

            Area a2 = w2.getProducer().getAreas().get(0);
            Region r2 = a2.getRegions().get(0);
            Country c2 = r2.getCountry();

            result = c1.compareTo(c2);
            if (result != 0) {
                return result;
            }

            result = r1.compareTo(r2);
            if (result != 0) {
                return result;
            }

            result = a1.compareTo(a2);
            if (result != 0) {
                return result;
            }
        }

        result = w1.getProducer().compareTo(w2.getProducer());
        if (result != 0) {
            return result;
        }

        result = w1.getName().compareTo(w2.getName());
        if (result != 0) {
            return result;
        }

        result = w1.getVintage().compareTo(w2.getVintage());
        if (result != 0) {
            return result;
        }

        result = w1.getSize().compareTo(w2.getSize());
        if (result != 0) {
            return result;
        }

        return 0;
    }
}

