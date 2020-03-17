/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.model.Region;
import info.mywinecellar.ui.RegionUI;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class RegionService {

    @Inject
    EntityManager em;

    @Inject
    RegionConverter regionConverter;

    /**
     * Find Region by it's ID
     *
     * @param id Long id
     * @return Region entity
     */
    public Region findById(Long id) {
        try {
            return em.find(Region.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Region by it's name
     *
     * @param name String name
     * @return Region entity
     */
    public Region findByName(String name) {
        try {
            Query query = em.createQuery("SELECT r FROM Region r WHERE r.name = :name");
            query.setParameter("name", name);
            return (Region) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Region by it's lowercase name
     * If it has spaces between it will be separated by an underscore (_)
     * ie 'Napa Valley' would be napa_valley
     *
     * @param lcName    String lcName
     * @param countryId Long countryId
     * @return Region entity
     */
    public Region findByLowerCaseName(String lcName, Long countryId) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM region r WHERE lower(r.name) = :lc_name " +
                    "AND r.country_id = :country_id", Region.class);
            query.setParameter("lc_name", lcName);
            query.setParameter("country_id", countryId);
            return (Region) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save a Region entity
     *
     * @param region Region region
     */
    @Transactional
    public void save(Region region) {
        try {
            em.persist(region);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    /**
     * Helper service to find a Region by it's ID, convert it from the UI object, and then save
     *
     * @param ui       RegionUI ui
     * @param regionId Long regionId
     * @return Region entity
     */
    @Transactional
    public Region editRegion(RegionUI ui, Long regionId) {
        Region region = this.findById(regionId);
        region = regionConverter.toEntity(region, ui);
        this.save(region);
        return region;
    }
}
