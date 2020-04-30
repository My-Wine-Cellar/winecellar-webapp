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
 * Sorter for GenericTastingNotes DTOs
 */
public class GenericTastingNotesDtoSorter implements Comparator<GenericTastingNotesDto> {
    private WineDtoSorter wineSorter;

    /**
     * Default constructor that doesn't take country into account
     */
    public GenericTastingNotesDtoSorter() {
        this(false);
    }

    /**
     * Constructor
     * @param country Should country be considered
     */
    public GenericTastingNotesDtoSorter(boolean country) {
        wineSorter = new WineDtoSorter(country);
    }

    /**
     * Compare two generic tasting notes entities
     * @param g1 Instance one
     * @param g2 Instance two
     * @return 0 if equals, otherwise result of WineSorter
     */
    public int compare(GenericTastingNotesDto g1, GenericTastingNotesDto g2) {
        if (g1.getId().equals(g2.getId())) {
            return 0;
        }

        return wineSorter.compare(g1.getWine(), g2.getWine());
    }
}

