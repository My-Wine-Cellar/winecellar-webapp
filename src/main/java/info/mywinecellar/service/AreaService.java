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
import info.mywinecellar.model.Area;
import info.mywinecellar.model.Producer;
import info.mywinecellar.ui.AreaUI;
import info.mywinecellar.ui.ProducerUI;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Area service
 */
@Component
public class AreaService {

    @Inject
    private EntityManager em;

    @Inject
    private AreaConverter areaConverter;

    @Inject
    private ProducerService producerService;

    @Inject
    private ProducerConverter producerConverter;

    /**
     * Find by id
     * @param id The identifier
     * @return The area
     */
    public Area findById(Long id) {
        try {
            return em.find(Area.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find by name
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
     * Save
     * @param a The area
     */
    @Transactional
    public void save(Area a) {
        try {
            em.persist(a);
        } catch (Exception e) {
            // Log
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    /**
     * Edit Area
     * @param ui AreaUI ui
     * @param areaId Long areaId
     * @return Area entity
     */
    @Transactional
    public Area editArea(AreaUI ui, Long areaId) {
        Area a = this.findById(areaId);
        a = areaConverter.toEntity(a, ui);
        this.save(a);
        return a;
    }

    /**
     * Add Producer to Area
     * @param ui ProducerUI ui
     * @param areaId Long areaId
     * @return Area entity
     */
    public Area areaAddProducer(ProducerUI ui, Long areaId) {
        Area area = this.findById(areaId);
        Producer p = producerConverter.toEntity(null, ui);
        area.getProducers().add(p);
        producerService.save(p);
        return area;
    }

}
