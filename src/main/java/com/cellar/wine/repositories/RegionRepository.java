package com.cellar.wine.repositories;

import com.cellar.wine.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByName(String name);
}
