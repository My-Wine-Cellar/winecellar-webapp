/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.WineDetailsDto;
import info.mywinecellar.dto.WineDto;
import info.mywinecellar.dto.WineDtoSorter;
import info.mywinecellar.model.Closure;
import info.mywinecellar.model.Color;
import info.mywinecellar.model.Producer;
import info.mywinecellar.model.Shape;
import info.mywinecellar.model.Type;
import info.mywinecellar.model.Wine;
import info.mywinecellar.util.Image;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WineConverter {

    /**
     * Convert entity to dto
     *
     * @param wine wine
     * @return dto object
     */
    public WineDto toDto(Wine wine) {
        if (wine == null) {
            throw new IllegalStateException("Wine is null");
        }
        return new WineDto(wine);
    }

    /**
     * Convert entity to dto
     *
     * @param wine wine
     * @return dto object
     */
    public WineDetailsDto toDetailsDto(Wine wine) {
        if (wine == null) {
            throw new IllegalStateException("Wine is null");
        }
        return new WineDetailsDto(wine);
    }

    /**
     * Convert entity list to dto list
     *
     * @param wines wines
     * @return dto list
     */
    public List<WineDto> toDto(List<Wine> wines) {
        if (wines == null) {
            throw new IllegalStateException("Wine list is null");
        }
        List<WineDto> result = new ArrayList<>();
        wines.forEach(wine -> result.add(toDto(wine)));
        result.sort(new WineDtoSorter());
        return result;
    }

    /**
     * Create a Wine entity
     *
     * @param entity entity
     * @param dto    dto
     * @return entity
     */
    public Wine toEntity(Wine entity, WineDto dto) {
        Producer producer = new Producer();
        producer.setId(dto.getProducerId());

        Color color = new Color();
        color.setId(dto.getColorId());
        Type type = new Type();
        type.setId(dto.getTypeId());
        Shape shape = new Shape();
        shape.setId(dto.getShapeId());
        Closure closure = new Closure();
        closure.setId(dto.getClosureId());

        if (entity == null) {
            entity = new Wine(dto.getName(), dto.getVintage(), dto.getAlcohol(), dto.getSize(),
                    dto.getAcid(), dto.getPH(), dto.getBottleAging(), dto.getDescription(), dto.getWeblink(),
                    producer, closure, shape, color, type, Image.decode(dto.getImage()));
        } else {
            entity.setName(dto.getName());
            entity.setVintage(dto.getVintage());
            entity.setAlcohol(dto.getAlcohol());
            entity.setSize(dto.getSize());
            entity.setAcid(dto.getAcid());
            entity.setPH(dto.getPH());
            entity.setBottleAging(dto.getBottleAging());
            entity.setDescription(dto.getDescription());
            entity.setWeblink(dto.getWeblink());
            entity.setImage(Image.decode(dto.getImage()));
            entity.setProducer(producer);
            entity.setColor(color);
            entity.setType(type);
            entity.setShape(shape);
            entity.setClosure(closure);
        }
        return entity;
    }
}
