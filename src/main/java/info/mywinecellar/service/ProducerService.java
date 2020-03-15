/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Producer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Producer service
 */
@Component
public class ProducerService {

    @Inject
    private EntityManager em;

    /**
     * Find by id
     * @param id The identifier
     * @return The producer
     */
    public Producer findById(Long id) {
        try {
            return em.find(Producer.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find by name
     *
     * @param name The name
     * @return The producer
     */
    public Producer findByName(String name) {
        try {
            Query q = em.createQuery("SELECT p FROM Producer p WHERE p.name = :name");
            q.setParameter("name", name);
            return (Producer) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save
     * @param p The producer
     */
    @Transactional
    public void save(Producer p) {
        try {
            em.persist(p);
        } catch (Exception e) {
            // Log
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
