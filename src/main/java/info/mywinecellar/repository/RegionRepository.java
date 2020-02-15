/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends JpaRepository<Region, Long> {

    /**
     * @param lcName    lcName
     * @param countryId countryId
     * @return Region
     */
    @Query(nativeQuery = true, value = "SELECT * FROM region r WHERE lower(r.name) = " +
            ":lc_name AND r.country_id = :country_id")
    Region findByLowerCaseName(@Param("lc_name") String lcName, @Param("country_id") Long countryId);
}
