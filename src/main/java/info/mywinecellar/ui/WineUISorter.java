/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import java.util.Comparator;

/**
 * Sorter for WineUI beans
 */
public class WineUISorter implements Comparator<WineUI> {

    /**
     * Default constructor
     */
    public WineUISorter() {
    }

    /**
     * Compare two wines
     * @param w1 The first wine
     * @param w2 The second wine
     * @return 0 if equals, otherwise based on name and vintage
     */
    public int compare(WineUI w1, WineUI w2) {
        int result;

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

