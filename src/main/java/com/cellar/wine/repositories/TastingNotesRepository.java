/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.repositories;

import com.cellar.wine.models.GenericTastingNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TastingNotesRepository extends JpaRepository<GenericTastingNotes, Long> {

    @Query("SELECT gtn from GenericTastingNotes gtn WHERE gtn.user.id = :userid AND gtn.id = :id")
    GenericTastingNotes findByUser(@Param("userid") Integer userId, @Param("id") Long id);

    @Query("SELECT gtn FROM GenericTastingNotes gtn WHERE gtn.user.id = :userid AND gtn.wine.id = :wineid")
    GenericTastingNotes findByWine(@Param("userid") Integer userId, @Param("wineid") Long wineId);
}
