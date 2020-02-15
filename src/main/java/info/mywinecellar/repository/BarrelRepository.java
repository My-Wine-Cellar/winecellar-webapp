/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Barrel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BarrelRepository extends JpaRepository<Barrel, Long> {

    /**
     * @param lcName lcName
     * @return Barrel's
     */
    @Query(nativeQuery = true, value = "SELECT * FROM barrel b WHERE lower(b.name) LIKE :lc_name ORDER BY b.id")
    List<Barrel> findByLowerCaseName(@Param("lc_name") String lcName);
}
