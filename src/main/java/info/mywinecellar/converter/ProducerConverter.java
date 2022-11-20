/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Producer;
import info.mywinecellar.util.Image;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for {@link Producer} and {@link ProducerDto} conversion
 */
public final class ProducerConverter {

    private ProducerConverter() {
    }

    /**
     * Create a list of Dto objects
     *
     * @param producers The producers
     * @return The Dto objects
     */
    public static List<ProducerDto> toDto(Set<Producer> producers) {
        return producers.stream()
                .map(ProducerConverter::toDto)
                .sorted(Comparator.comparing(ProducerDto::getName))
                .collect(Collectors.toList());
    }

    /**
     * Create a Dto object
     *
     * @param p A producer
     * @return The Dto
     */
    public static ProducerDto toDto(Producer p) {
        return Optional.ofNullable(p)
                .map(ProducerDto::new)
                .orElse(null);
    }

    /**
     * Create a Producer entity
     *
     * @param p   The entity
     * @param dto The Dto object
     * @return The new entity
     */
    public static Producer toEntity(Producer p, ProducerDto dto) {
        if (p == null) {
            p = new Producer(dto.getName(), dto.getDescription(), dto.getPhone(),
                    dto.getFax(), dto.getEmail(), dto.getWebsite(),
                    Image.decode(dto.getImage()));
        } else {
            p.setName(dto.getName());
            p.setDescription(dto.getDescription());
            p.setPhone(dto.getPhone());
            p.setFax(dto.getFax());
            p.setEmail(dto.getEmail());
            p.setWebsite(dto.getWebsite());
            p.setImage(Image.decode(dto.getImage()));
        }

        return p;
    }
}
