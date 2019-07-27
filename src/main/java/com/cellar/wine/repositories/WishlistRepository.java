package com.cellar.wine.repositories;

import com.cellar.wine.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userid AND w.id = :id")
    Wishlist findByUser(@Param("userid") Integer userId, @Param("id") Long id);

    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userid AND w.wine.id = :wineid")
    Wishlist findByWine(@Param("userid") Integer userId, @Param("wineid") Long wineId);

}
