/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.model.Grape;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for {@link Grape} and {@link GrapeDto} conversion
 */
public final class GrapeConverter {

    private GrapeConverter() {
    }

    /**
     * Create a list of Dto objects
     *
     * @param grapes The grapes
     * @return The Dto objects
     */
    public static List<GrapeDto> toDto(List<Grape> grapes) {
        return convert(grapes);
    }

    /**
     * Create a list of Dto objects
     *
     * @param grapes The grapes
     * @return The Dto objects
     */
    public static List<GrapeDto> toDto(Set<Grape> grapes) {
        return convert(grapes);
    }

    /**
     * Create a Dto object
     *
     * @param g A grape
     * @return The Dto
     */
    public static GrapeDto toDto(Grape g) {
        return Optional.ofNullable(g)
                .map(GrapeDto::new)
                .orElse(null);
    }

    /**
     * Create a Grape entity
     *
     * @param g   The entity
     * @param dto The Dto object
     * @return The new entity
     */
    public static Grape toEntity(Grape g, GrapeDto dto) {
        if (g == null) {
            g = new Grape(dto.getName(), dto.getColor(), dto.getDescription(), dto.getWeblink());
        } else {
            g.setDescription(dto.getDescription());
            g.setWeblink(dto.getWeblink());
        }

        return g;
    }

    /**
     * Create a list of Dto objects
     *
     * @param grapes The grapes
     * @return The Dto objects
     */
    private static List<GrapeDto> convert(Collection<Grape> grapes) {
        return Stream.ofNullable(grapes)
                .flatMap(Collection::stream)
                .map(GrapeConverter::toDto)
                .sorted(Comparator.comparing(GrapeDto::getName))
                .collect(Collectors.toList());
    }

}
