package com.cellar.wine.repositories;

import com.cellar.wine.models.Fermentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FermentationRepository extends JpaRepository<Fermentation, Long> {
}