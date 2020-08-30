/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.dto.CountryDto;
import info.mywinecellar.model.Country;

import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class CountryService extends AbstractService<Country> {

    @Inject
    private CountryConverter countryConverter;

    /**
     * Super constructor
     */
    public CountryService() {
        super(Country.class);
    }

    /**
     * Find Country by it's name
     *
     * @param name String name
     * @return Country entity
     */
    public Country findByName(String name) {
        try {
            Query query = em.createQuery("SELECT c FROM Country c WHERE c.name = :name");
            query.setParameter("name", name);
            return (Country) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Country by it's lowercase name
     * If it has spaces between it will be separated by an underscore (_)
     * ie 'United States' would be united_states
     *
     * @param lcName String lcName
     * @return Country entity
     */
    public Country findByLowerCaseName(String lcName) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM country c WHERE lower(c.name) = :lc_name",
                    Country.class);
            query.setParameter("lc_name", lcName);
            return (Country) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Countries that have wine growing regions
     *
     * @return A list of Countries
     */
    public Set<Country> findWithRegions() {
        try {
            Query query = em.createNativeQuery("SELECT DISTINCT country.id, country.name, " +
                    "country.description, country.flag, country.weblink " +
                    "FROM country JOIN region on country.id = region.country_id " +
                    "ORDER BY country.name", Country.class);
            return new TreeSet<>(query.getResultList());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Helper service to find a Country by it's ID, convert it from the Dto object, and then save
     *
     * @param dto       CountryDto dto
     * @param countryId Long countryId
     * @return Country entity
     */
    @Transactional
    public Country editCountry(CountryDto dto, Long countryId) {
        Country country = countryConverter.toEntity(this.findById(countryId), dto);
        this.save(country);
        log.info("Updated Country: {} ", country.getName());
        return country;
    }

}
