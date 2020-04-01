/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.AreaConverter;
import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.dto.AreaDto;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Producer;

import javax.inject.Inject;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Area service
 */
@Component
public class AreaService extends AbstractService<Area> {

    protected AreaService() {
        super(Area.class);
    }

    @Inject
    private AreaConverter areaConverter;

    @Inject
    private ProducerService producerService;

    @Inject
    private ProducerConverter producerConverter;

    /**
     * Find by name
     *
     * @param name The name
     * @return The area
     */
    public Area findByName(String name) {
        try {
            Query q = em.createQuery("SELECT a FROM Area a WHERE a.name = :name");
            q.setParameter("name", name);
            return (Area) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Edit Area
     *
     * @param dto    AreaDto dto
     * @param areaId Long areaId
     * @return Area entity
     */
    @Transactional
    public Area editArea(AreaDto dto, Long areaId) {
        Area area = this.findById(areaId);
        area = areaConverter.toEntity(area, dto);
        this.save(area);
        return area;
    }

    /**
     * Add Producer to Area
     *
     * @param dto    ProducerDto dto
     * @param areaId Long areaId
     * @return Area entity
     */
    public Area areaAddProducer(ProducerDto dto, Long areaId) {
        Area area = this.findById(areaId);
        Producer producer = producerConverter.toEntity(null, dto);
        area.getProducers().add(producer);
        producerService.save(producer);
        return area;
    }

}
