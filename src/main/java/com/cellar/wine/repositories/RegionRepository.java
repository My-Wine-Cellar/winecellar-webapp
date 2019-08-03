package com.cellar.wine.repositories;

import com.cellar.wine.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM region r WHERE lower(r.name) = :lc_name AND r.country_id = :country_id")
    Region findByLowerCaseName(@Param("lc_name") String lcName, @Param("country_id") Long countryId);
}
