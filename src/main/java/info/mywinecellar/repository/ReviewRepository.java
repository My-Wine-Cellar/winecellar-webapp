/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * @param userId userId
     * @param id     id
     * @return Review
     */
    @Query("SELECT r FROM Review r WHERE r.user.id = :userid AND r.id = :id")
    Review findByUser(@Param("userid") Integer userId, @Param("id") Long id);

    /**
     * @param userId userId
     * @param wineId wineId
     * @return Review
     */
    @Query("SELECT r FROM Review r WHERE r.user.id = :userid AND r.wine.id = :wineid")
    Review findByWine(@Param("userid") Integer userId, @Param("wineid") Long wineId);

}
