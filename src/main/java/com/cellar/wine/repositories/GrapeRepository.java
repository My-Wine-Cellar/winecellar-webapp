package com.cellar.wine.repositories;

import com.cellar.wine.models.Grape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrapeRepository extends JpaRepository<Grape, Long> {

    Grape findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM grape WHERE grape.color = 'White' ORDER BY grape.name")
    List<Grape> getWhiteGrapes();

    @Query(nativeQuery = true, value = "SELECT * FROM grape WHERE grape.color = 'Red' ORDER BY grape.name")
    List<Grape> getRedGrapes();

    @Query(nativeQuery = true, value = "SELECT * FROM grape g WHERE lower(g.name) = :lc_name")
    Grape findByLowerCaseName(@Param("lc_name") String lcName);
}
