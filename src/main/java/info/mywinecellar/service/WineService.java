/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.WineConverter;
import info.mywinecellar.model.Wine;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class WineService {

    @Inject
    EntityManager em;

    @Inject
    WineConverter wineConverter;

    /**
     * Find Wine by ID
     *
     * @param id Long id
     * @return Wine entity
     */
    public Wine findById(Long id) {
        try {
            return em.find(Wine.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Wine by name
     *
     * @param name String name
     * @return Wine entity
     */
    Wine findByName(String name) {
        try {
            Query query = em.createQuery("SELECT w FROM Wine w WHERE w.name = :name");
            query.setParameter("name", name);
            return (Wine) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find all Wine's by name
     *
     * @param name String name
     * @return A list of Wine's
     */
    List<Wine> findAllByName(String name) {
        try {
            Query query = em.createQuery("SELECT w FROM Wine w WHERE w.name = :name");
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save Wine
     *
     * @param wine Wine wine
     */
    @Transactional
    public void save(Wine wine) {
        try {
            em.persist(wine);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    /**
     * Update Wine
     *
     * @param wine Wine wine
     */
    @Transactional
    public void update(Wine wine) {
        try {
            em.merge(wine);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
