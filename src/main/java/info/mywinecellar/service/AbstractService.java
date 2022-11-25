/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.BaseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import javax.inject.Inject;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractService<T extends BaseEntity> {

    @Inject
    EntityManager em;

    private final Class<T> entityClass;

    /**
     * Constructor
     * Allows for initialization of our T being passed in
     *
     * @param entityClass The class being initialized
     */
    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Find an entity by it's ID
     *
     * @param id Long id
     * @return entity
     */
    public T findById(Long id) {
        return em.find(entityClass, id);
    }

    /**
     * Find all
     *
     * @return the list of entities
     */
    public Set<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        return new HashSet<>(em.createQuery(query).getResultList());
    }

    /**
     * Save the entity
     *
     * @param entity entity
     */
    @Transactional
    public void save(T entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            log.error(String.valueOf(entity.getClass()));
            log.error(e.getMessage());
            log.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Delete the entity
     *
     * @param entity entity
     */
    @Transactional
    public void delete(T entity) {
        try {
            em.remove(entity);
        } catch (Exception e) {
            log.error(String.valueOf(entity.getClass()));
            log.error(e.getMessage());
            log.trace(Arrays.toString(e.getStackTrace()));
        }
    }

}
