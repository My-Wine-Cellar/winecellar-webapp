/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.repositories;

import com.cellar.wine.models.Barrel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BarrelRepository extends JpaRepository<Barrel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM barrel b WHERE lower(b.name) LIKE :lc_name ORDER BY b.id")
    List<Barrel> findByLowerCaseName(@Param("lc_name") String lcName);
}
