/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.GrapeConverter;
import info.mywinecellar.dto.GrapeDto;
import info.mywinecellar.model.Grape;

import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Grape service
 */
@Component
public class GrapeService extends AbstractService<Grape> {

    @Inject
    private GrapeConverter grapeConverter;

    /**
     * Constructor
     */
    public GrapeService() {
        super(Grape.class);
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
     * Find all white grapes
     *
     * @return The grapes
     */
    public Set<Grape> getWhiteGrapes() {
        try {
            Query q =
                    em.createNativeQuery("SELECT * FROM grape WHERE grape.color = 'White' ORDER BY grape.name",
                            Grape.class);
            return new TreeSet<>(q.getResultList());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find all red grapes
     *
     * @return The grapes
     */
    public Set<Grape> getRedGrapes() {
        try {
            Query q =
                    em.createNativeQuery("SELECT * FROM grape WHERE grape.color = 'Red' ORDER BY grape.name",
                            Grape.class);
            return new TreeSet<>(q.getResultList());
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
     * Edit Grape
     *
     * @param dto     GrapeDto dto
     * @param grapeId Long grapeId
     * @return Grape entity
     */
    @Transactional
    public Grape editGrape(GrapeDto dto, Long grapeId) {
        Grape entity = grapeConverter.toEntity(this.findById(grapeId), dto);
        this.save(entity);
        return entity;
    }
}
