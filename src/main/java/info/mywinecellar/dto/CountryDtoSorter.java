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
public class CountryDtoSorter implements Comparator<CountryDto> {

    /**
     * Default constructor
     */
    public CountryDtoSorter() {
    }

    /**
     * Compare two countries
     * @param c1 The first country
     * @param c2 The second country
     * @return 0 if equals, otherwise based on name
     */
    public int compare(CountryDto c1, CountryDto c2) {
        return c1.getName().compareTo(c2.getName());
    }
}

