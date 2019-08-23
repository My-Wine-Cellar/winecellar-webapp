package com.cellar.wine.repositories;

import com.cellar.wine.models.Maceration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacerationRepository extends JpaRepository<Maceration, Long> {
}