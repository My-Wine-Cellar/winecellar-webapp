/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.util;

import info.mywinecellar.model.Producer;

import java.util.Comparator;

/**
 * Sorter for Producer entities
 */
public class ProducerSorter implements Comparator<Producer> {

    /**
     * Default constructor
     */
    public ProducerSorter() {
    }

    /**
     * Compare two producer entities
     * @param p1 Instance one
     * @param p2 Instance two
     * @return 0 if equals, otherwise result of name compare
     */
    public int compare(Producer p1, Producer p2) {
        if (p1.getId().equals(p2.getId())) {
            return 0;
        }

        return p1.getName().compareTo(p2.getName());
    }
}

