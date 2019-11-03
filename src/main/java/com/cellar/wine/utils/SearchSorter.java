package com.cellar.wine.utils;

import com.cellar.wine.ui.SearchUI;

import java.util.Comparator;

/**
 * Sorter for SearchUI objects
 */
public class SearchSorter implements Comparator<SearchUI> {

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
    public int compare(SearchUI s1, SearchUI s2) {
        int c = s1.getRank().compareTo(s2.getRank());

        if (c != 0) {
            return -c;
        }

        return s1.getName().compareTo(s2.getName());
    }
}
