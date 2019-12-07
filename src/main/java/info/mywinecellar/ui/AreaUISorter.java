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
 * Sorter for AreaUI beans
 */
public class AreaUISorter implements Comparator<AreaUI> {

    /**
     * Default constructor
     */
    public AreaUISorter() {
    }

    /**
     * Compare two countries
     * @param a1 The first area
     * @param a2 The second area
     * @return 0 if equals, otherwise based on name
     */
    public int compare(AreaUI a1, AreaUI a2) {
        return a1.getName().compareTo(a2.getName());
    }
}

