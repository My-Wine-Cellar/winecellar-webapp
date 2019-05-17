package com.cellar.wine.repositories;

import com.cellar.wine.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Area findByName(String name);
}
