/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.GenericTastingNotesDto;
import info.mywinecellar.model.GenericTastingNotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link GenericTastingNotes} and {@link GenericTastingNotesDto} conversion
 */
public final class TastingNotesConverter {

    private TastingNotesConverter() {
    }

    /**
     * Convert entity to dto
     *
     * @param gtn gtn
     * @return dto object
     */
    public static GenericTastingNotesDto toDto(GenericTastingNotes gtn) {
        if (gtn == null) {
            throw new IllegalStateException("Tasting notes is null");
        }
        return new GenericTastingNotesDto(gtn);
    }

    /**
     * Convert entity list to ui list
     *
     * @param notes notes
     * @return ui list
     */
    public static List<GenericTastingNotesDto> toDto(Set<GenericTastingNotes> notes) {
        if (notes == null) {
            throw new IllegalStateException("Tasting notes list is null");
        }
        List<GenericTastingNotesDto> result = new ArrayList<>();
        notes.forEach(tasting -> result.add(toDto(tasting)));
        /* SORTING */
        return result;
    }
}
