/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Area;

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
}
