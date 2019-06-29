package com.cellar.wine.utils;

import com.cellar.wine.models.GenericTastingNotes;

import java.util.Comparator;

/**
 * Sorter for GenericTastingNotes entities
 */
public class GenericTastingNotesSorter implements Comparator<GenericTastingNotes> {
    private WineSorter wineSorter;

    public GenericTastingNotesSorter() {
        this(false);
    }

    public GenericTastingNotesSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    public int compare(GenericTastingNotes g1, GenericTastingNotes g2) {
        if (g1.getId().equals(g2.getId()))
            return 0;

        return wineSorter.compare(g1.getWine(), g2.getWine());
    }
}

