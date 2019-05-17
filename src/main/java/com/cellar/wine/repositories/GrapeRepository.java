package com.cellar.wine.repositories;

import com.cellar.wine.models.Grape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrapeRepository extends JpaRepository<Grape, Long> {

    Grape findGrapeByName(String name);
}
