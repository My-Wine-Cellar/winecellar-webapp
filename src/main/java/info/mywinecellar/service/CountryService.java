/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.CountryConverter;
import info.mywinecellar.model.Country;
import info.mywinecellar.ui.CountryUI;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class CountryService {

    @Inject
    private EntityManager em;

    @Inject
    private CountryConverter countryConverter;

    /**
     * Find Country by it's ID
     *
     * @param id Long id
     * @return Country entity
     */
    public Country findById(Long id) {
        try {
            return em.find(Country.class, id);
        } catch (Exception e) {
            return null;
        }
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
    public List<Country> findWithRegions() {
        try {
            Query query = em.createNativeQuery("SELECT DISTINCT country.id, country.name, " +
                    "country.description, country.flag, country.weblink " +
                    "FROM country JOIN region on country.id = region.country_id " +
                    "ORDER BY country.name", Country.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save a Country entity
     *
     * @param country Country country
     */
    @Transactional
    public void save(Country country) {
        try {
            em.persist(country);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    /**
     * Helper service to find a Country by it's ID, convert it from the UI object, and then save
     *
     * @param ui        CountryUI ui
     * @param countryId Long countryId
     * @return Country entity
     */
    @Transactional
    public Country editCountry(CountryUI ui, Long countryId) {
        Country country = this.findById(countryId);
        country = countryConverter.toEntity(country, ui);
        this.save(country);
        return country;
    }

}
