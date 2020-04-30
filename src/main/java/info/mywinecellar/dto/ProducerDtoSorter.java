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
public class ProducerDtoSorter implements Comparator<ProducerDto> {

    /**
     * Default constructor
     */
    public ProducerDtoSorter() {
    }

    /**
     * Compare two producers
     * @param p1 The first producer
     * @param p2 The second producer
     * @return 0 if equals, otherwise based on name
     */
    public int compare(ProducerDto p1, ProducerDto p2) {
        if (p1.getId().equals(p2.getId())) {
            return 0;
        }

        return p1.getName().compareTo(p2.getName());
    }
}

