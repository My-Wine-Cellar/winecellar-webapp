package com.cellar.wine.repositories;

import com.cellar.wine.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.user.id = :userid AND r.id = :id")
    Review findByUser(@Param("userid") Integer userId, @Param("id") Long id);

    @Query("SELECT r FROM Review r WHERE r.user.id = :userid AND r.wine.id = :wineid")
    Review findByWine(@Param("userid") Integer userId, @Param("wineid") Long wineId);

}
