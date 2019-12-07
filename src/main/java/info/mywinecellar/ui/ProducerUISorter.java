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
 * Sorter for ProducerUI beans
 */
public class ProducerUISorter implements Comparator<ProducerUI> {

    /**
     * Default constructor
     */
    public ProducerUISorter() {
    }

    /**
     * Compare two countries
     * @param p1 The first producer
     * @param p2 The second producer
     * @return 0 if equals, otherwise based on name
     */
    public int compare(ProducerUI p1, ProducerUI p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

