/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Producer;
import info.mywinecellar.util.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * Producer converter
 */
@Component
public class ProducerConverter {

    /**
     * Create a list of Dto objects
     * @param producers The producers
     * @return The Dto objects
     */
    public List<ProducerDto> toDto(Set<Producer> producers) {
        if (producers == null) {
            throw new IllegalStateException("Producers is null");
        }

        List<ProducerDto> result = new ArrayList<>();
        producers.forEach(producer -> result.add(toDto(producer)));
        /* SORTING */
        return result;
    }

    /**
     * Create a Dto object
     * @param p A producer
     * @return The Dto
     */
    public ProducerDto toDto(Producer p) {
        if (p == null) {
            throw new IllegalStateException("Producer is null");
        }

        return new ProducerDto(p);
    }

    /**
     * Create a Producer entity
     * @param p The entity
     * @param dto The Dto object
     * @return The new entity
     */
    public Producer toEntity(Producer p, ProducerDto dto) {
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
