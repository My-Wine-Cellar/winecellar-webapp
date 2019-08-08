package com.cellar.wine.repositories;

import com.cellar.wine.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM country c WHERE lower(c.name) = :lc_name")
    Country findByLowerCaseName(@Param("lc_name") String lcName);

    @Query(nativeQuery = true, value = "SELECT DISTINCT c.id, c.description, c.name, c.weblink " +
                                       "FROM country c JOIN region r ON c.id = r.country_id ORDER BY c.name")
    List<Country> findWithRegions();
}
