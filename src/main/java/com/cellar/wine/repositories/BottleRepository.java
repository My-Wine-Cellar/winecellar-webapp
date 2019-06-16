package com.cellar.wine.repositories;

import com.cellar.wine.models.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BottleRepository extends JpaRepository<Bottle, Long> {
}
