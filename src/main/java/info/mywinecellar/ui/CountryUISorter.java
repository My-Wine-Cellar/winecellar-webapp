/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import java.util.Comparator;

/**
 * Sorter for CountryUI beans
 */
public class CountryUISorter implements Comparator<CountryUI> {

    /**
     * Default constructor
     */
    public CountryUISorter() {
    }

    /**
     * Compare two countries
     * @param c1 The first country
     * @param c2 The second country
     * @return 0 if equals, otherwise based on name
     */
    public int compare(CountryUI c1, CountryUI c2) {
        return c1.getName().compareTo(c2.getName());
    }
}

