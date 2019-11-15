/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.ui;

import java.util.Comparator;

/**
 * Sorter for GrapeUI beans
 */
public class GrapeUISorter implements Comparator<GrapeUI> {

    public GrapeUISorter() {
    }

    public int compare(GrapeUI wg1, GrapeUI wg2) {
        int result;

        if (wg1.getPercentage() != null && wg2.getPercentage() != null) {
            result = wg1.getPercentage().compareTo(wg2.getPercentage());
            if (result != 0)
                return -result;
        }

        return wg1.getName().compareTo(wg2.getName());
    }
}

