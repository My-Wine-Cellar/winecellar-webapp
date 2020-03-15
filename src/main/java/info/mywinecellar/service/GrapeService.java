/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Grape;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Grape service
 */
@Component
public class GrapeService {

    @Inject
    private EntityManager em;

    /**
     * Find by id
     * @param id The identifier
     * @return The grape
     */
    public Grape findById(Long id) {
        try {
            return em.find(Grape.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find by name
     *
     * @param name The name
     * @return The grape
     */
    public Grape findByName(String name) {
        try {
            Query q = em.createQuery("SELECT g FROM Grape g WHERE g.name = :name");
            q.setParameter("name", name);
            return (Grape) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find all
     *
     * @return The grape
     */
    public List<Grape> findAll() {
        try {
            Query q = em.createQuery("SELECT g FROM Grape g");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find all white grapes
     *
     * @return The grapes
     */
    public List<Grape> getWhiteGrapes() {
        try {
            Query q =
                em.createNativeQuery("SELECT * FROM grape WHERE grape.color = 'White' ORDER BY grape.name",
                                     Grape.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find all red grapes
     *
     * @return The grapes
     */
    public List<Grape> getRedGrapes() {
        try {
            Query q =
                em.createNativeQuery("SELECT * FROM grape WHERE grape.color = 'Red' ORDER BY grape.name",
                                     Grape.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find by lower case name
     *
     * @param lcName The lower case name
     * @return The grape
     */
    public Grape findByLowerCaseName(String lcName) {
        try {
            Query q = em.createNativeQuery("SELECT * FROM grape g WHERE lower(g.name) = :lc_name", Grape.class);
            q.setParameter("lc_name", lcName);
            return (Grape) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save
     * @param g The grape
     */
    @Transactional
    public void save(Grape g) {
        try {
            em.persist(g);
        } catch (Exception e) {
            // Log
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
