/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.BaseEntity;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService<T extends BaseEntity> {

    /**
     * Service logger
     */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    EntityManager em;

    private Class<T> entityClass;

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
            System.out.println(entity.getClass());
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
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
            System.out.println(entity.getClass());
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

}
