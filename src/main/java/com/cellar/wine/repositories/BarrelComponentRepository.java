package com.cellar.wine.repositories;

import com.cellar.wine.models.BarrelComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarrelComponentRepository extends JpaRepository<BarrelComponent, Long> {
}
