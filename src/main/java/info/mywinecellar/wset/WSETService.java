/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * WSET service
 */
@Component
public class WSETService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EntityManager em;

    /**
     * Find by id
     * @param id The identifier
     * @return The WSET
     */
    public WSET findById(Long id) {
        try {
            return em.find(WSET.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find by user
     * @param userId The user identifier
     * @param id The WSET identifier
     * @return The WSET instance
     */
    public WSET findByUser(Integer userId, Long id) {
        try {
            Query q = em.createQuery("SELECT w FROM WSET w WHERE w.id = :id AND w.user.id = :user");
            q.setParameter("id", id);
            q.setParameter("user", userId);
            return (WSET) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find by wine
     * @param userId The user identifier
     * @param wineId The wine identifier
     * @return The WSET instance
     */
    public WSET findByWine(Integer userId, Long wineId) {
        try {
            Query q = em.createQuery("SELECT w FROM WSET w WHERE w.user.id = :user AND w.wine.id = :wine");
            q.setParameter("user", userId);
            q.setParameter("wine", wineId);
            return (WSET) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find
     * @param <T> The type
     * @param clz The class
     * @param id The identifier
     * @return The entity
     */
    public <T> T find(Class<T> clz, Long id) {
        try {
            return em.find(clz, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save
     * @param w The WSET
     */
    @Transactional
    public void save(WSET w) {
        try {
            em.persist(w);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Delete
     * @param w The WSET
     */
    @Transactional
    public void delete(WSET w) {
        try {
            em.remove(w);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
