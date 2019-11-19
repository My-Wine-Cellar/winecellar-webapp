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
 * Sorter for BarrelUI beans
 */
public class BarrelUISorter implements Comparator<BarrelUI> {

    public BarrelUISorter() {
    }

    public int compare(BarrelUI b1, BarrelUI b2) {
        int result;

        result = b1.getPercentage().compareTo(b2.getPercentage());
        if (result != 0)
            return -result;

        result = b1.getAging().compareTo(b2.getAging());
        if (result != 0)
            return -result;

        result = b1.getSize().compareTo(b2.getSize());
        if (result != 0)
            return result;

        return b1.getName().compareTo(b2.getName());
    }
}

