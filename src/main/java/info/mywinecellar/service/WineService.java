/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Wine;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class WineService extends AbstractService<Wine> {

    protected WineService() {
        super(Wine.class);
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
    Set<Wine> findAllByName(String name) {
        try {
            Query query = em.createQuery("SELECT w FROM Wine w WHERE w.name = :name");
            query.setParameter("name", name);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find a wine by it's lowercase name
     *
     * @param name String name
     * @return Wine entity
     */
    public Wine findByLowerCaseName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Wine> query = cb.createQuery(Wine.class);
        Root<Wine> root = query.from(Wine.class);
        Predicate predicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        query.where(predicate);
        return em.createQuery(query).getSingleResult();
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
