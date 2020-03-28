/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.util;

import info.mywinecellar.dto.SearchDto;

import java.util.Comparator;

/**
 * Sorter for SearchUI objects
 */
public class SearchSorter implements Comparator<SearchDto> {

    /**
     * Constructor
     */
    public SearchSorter() {
    }

    /**
     * Compare two search objects
     * @param s1 First object
     * @param s2 Second object
     * @return 0 if equals, 1 if s1 is smaller than s2, -1 if s1 is greater than s2
     */
    public int compare(SearchDto s1, SearchDto s2) {
        int c = s1.getRank().compareTo(s2.getRank());

        if (c != 0) {
            return -c;
        }

        return s1.getName().compareTo(s2.getName());
    }
}
