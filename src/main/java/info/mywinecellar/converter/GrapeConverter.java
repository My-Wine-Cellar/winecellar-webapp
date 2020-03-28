/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.dto.GrapeDtoSorter;
import info.mywinecellar.model.Grape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Grape converter
 */
@Component
public class GrapeConverter {

    /**
     * Create a list of Dto objects
     * @param grapes The grapes
     * @return The Dto objects
     */
    public List<GrapeDto> toDto(List<Grape> grapes) {
        if (grapes == null) {
            throw new IllegalStateException("Grapes is null");
        }

        List<GrapeDto> result = new ArrayList<>();
        for (Grape g : grapes) {
            result.add(toDto(g));
        }
        Collections.sort(result, new GrapeDtoSorter());

        return result;
    }

    /**
     * Create a Dto object
     * @param g A grape
     * @return The Dto
     */
    public GrapeDto toDto(Grape g) {
        if (g == null) {
            throw new IllegalStateException("Grape is null");
        }

        return new GrapeDto(g);
    }

    /**
     * Create a Grape entity
     * @param g The entity
     * @param dto The Dto object
     * @return The new entity
     */
    public Grape toEntity(Grape g, GrapeDto dto) {
        if (g == null) {
            g = new Grape(dto.getName(), dto.getColor(), dto.getDescription(), dto.getWeblink());
        } else {
            g.setDescription(dto.getDescription());
            g.setWeblink(dto.getWeblink());
        }

        return g;
    }
}
