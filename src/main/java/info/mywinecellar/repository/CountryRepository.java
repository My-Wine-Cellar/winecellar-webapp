/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Long> {

    /**
     * @param lcName lcName
     * @return Country
     */
    @Query(nativeQuery = true, value = "SELECT * FROM country c WHERE lower(c.name) = :lc_name")
    Country findByLowerCaseName(@Param("lc_name") String lcName);

    /**
     * @return Country's that have wine producing Region's
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT c.id, c.name, c.flag, c.description, c.weblink " +
            "FROM country c JOIN region r ON c.id = r.country_id ORDER BY c.name")
    List<Country> findWithRegions();
}
