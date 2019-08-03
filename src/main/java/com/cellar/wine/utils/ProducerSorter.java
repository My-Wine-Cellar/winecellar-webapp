package com.cellar.wine.utils;

import com.cellar.wine.models.Producer;

import java.util.Comparator;

/**
 * Sorter for Producer entities
 */
public class ProducerSorter implements Comparator<Producer> {

    public ProducerSorter() {
    }

    public int compare(Producer p1, Producer p2) {
        
        if (p1.getId().equals(p2.getId()))
            return 0;

        return p1.getName().compareTo(p2.getName());
    }
}

