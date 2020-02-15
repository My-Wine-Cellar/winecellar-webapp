/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /**
     * @param userId userId
     * @param id     id
     * @return Wishlist
     */
    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userid AND w.id = :id")
    Wishlist findByUser(@Param("userid") Integer userId, @Param("id") Long id);

    /**
     * @param userId userId
     * @param wineId wineId
     * @return Wishlist
     */
    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userid AND w.wine.id = :wineid")
    Wishlist findByWine(@Param("userid") Integer userId, @Param("wineid") Long wineId);

}
