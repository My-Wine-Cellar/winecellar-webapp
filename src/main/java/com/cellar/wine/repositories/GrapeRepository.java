package com.cellar.wine.repositories;

import com.cellar.wine.models.Grape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface GrapeRepository extends JpaRepository<Grape, Long> {

    Grape findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM grape WHERE grape.color = 'White'")
    Set<Grape> getWhiteGrapes();

    @Query(nativeQuery = true, value = "SELECT * FROM grape WHERE grape.color = 'Red'")
    Set<Grape> getRedGrapes();
}
