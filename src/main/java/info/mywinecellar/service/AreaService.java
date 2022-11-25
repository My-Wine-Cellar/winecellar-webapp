/*
 * My-Wine-Cellar, copyright 2022
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

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

/**
 * Area service
 */
@Component
public class AreaService extends AbstractService<Area> {

    protected AreaService() {
        super(Area.class);
    }

    @Inject
    private ProducerService producerService;

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
     * Find Area by its name in lowercase form
     *
     * @param lcName String lowercase name
     * @return Area entity
     */
    public Area findByLowerCaseName(String lcName) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM area a WHERE lower(a.name) = :lc_name",
                    Area.class);
            query.setParameter("lc_name", lcName);
            return (Area) query.getSingleResult();
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
        area = AreaConverter.toEntity(area, dto);
        this.save(area);
        return area;
    }

    /**
     * Add Producer to Area
     *
     * @param areaId Long areaId
     * @param dto    ProducerDto dto
     * @return Area entity
     */
    public Area addProducer(Long areaId, ProducerDto dto) {
        Area area = this.findById(areaId);
        Producer producer = ProducerConverter.toEntity(null, dto);
        area.getProducers().add(producer);
        producerService.save(producer);
        return area;
    }

}
