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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return Optional.ofNullable(gtn)
                .map(GenericTastingNotesDto::new)
                .orElse(null);
    }

    /**
     * Convert entity list to ui list
     *
     * @param notes notes
     * @return ui list
     */
    public static List<GenericTastingNotesDto> toDto(Set<GenericTastingNotes> notes) {
        return notes.stream()
                .map(TastingNotesConverter::toDto)
                .collect(Collectors.toList());
    }
}
