package com.cellar.wine.repositories;

import com.cellar.wine.models.Tasted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TastedRepository extends JpaRepository<Tasted, Long> {
}
