/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.RegionConverter;
import info.mywinecellar.dto.RegionDto;
import info.mywinecellar.model.Region;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class RegionService extends AbstractService<Region> {

    protected RegionService() {
        super(Region.class);
    }

    @Inject
    RegionConverter regionConverter;

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
     * Helper service to find a Region by it's ID, convert from Dto object, then save
     *
     * @param dto      RegionDto dto
     * @param regionId Long regionId
     * @return Region entity
     */
    @Transactional
    public Region editRegion(RegionDto dto, Long regionId) {
        Region region = this.findById(regionId);
        region = regionConverter.toEntity(region, dto);
        this.save(region);
        log.info("Updated Region: {} ", region.getName());
        return region;
    }
}
