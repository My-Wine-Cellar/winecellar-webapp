/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Grape;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GrapeRepository extends JpaRepository<Grape, Long> {

    /**
     * @param name name
     * @return Grape
     */
    Grape findByName(String name);

    /**
     * @return Grapes that are white
     */
    @Query(nativeQuery = true, value = "SELECT * FROM grape WHERE grape.color = 'White' ORDER BY grape.name")
    List<Grape> getWhiteGrapes();

    /**
     * @return Grapes that are red
     */
    @Query(nativeQuery = true, value = "SELECT * FROM grape WHERE grape.color = 'Red' ORDER BY grape.name")
    List<Grape> getRedGrapes();

    /**
     * @param lcName lcName
     * @return Grape by it's lowercase name
     */
    @Query(nativeQuery = true, value = "SELECT * FROM grape g WHERE lower(g.name) = :lc_name")
    Grape findByLowerCaseName(@Param("lc_name") String lcName);
}
