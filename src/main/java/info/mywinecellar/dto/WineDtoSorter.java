/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import java.util.Comparator;

/**
 * Sorter for dto beans
 */
public class WineDtoSorter implements Comparator<WineDto> {

    private boolean country;

    /**
     * Default constructor
     */
    public WineDtoSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Account for country
     */
    public WineDtoSorter(boolean country) {
        this.country = country;
    }

    /**
     * Compare two wines
     * @param w1 The first wine
     * @param w2 The second wine
     * @return 0 if equals, otherwise based on name and vintage
     */
    public int compare(WineDto w1, WineDto w2) {
        int result;

        /*
          SORTING

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
         */

        result = w1.getName().compareTo(w2.getName());

        if (result == 0) {
            result = w1.getVintage().compareTo(w2.getVintage());
            if (result != 0) {
                return -result;
            }
        }

        return result;
    }
}

