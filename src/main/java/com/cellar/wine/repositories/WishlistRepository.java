package com.cellar.wine.repositories;

import com.cellar.wine.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.wine.id = :wineid AND w.user.id = :userid")
    Wishlist findByUser(@Param("wineid") Long wineId, @Param("userid") Integer userId);

}
