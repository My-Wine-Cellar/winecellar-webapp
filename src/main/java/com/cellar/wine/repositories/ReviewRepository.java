package com.cellar.wine.repositories;

import com.cellar.wine.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.wine.id = :wineid AND r.user.id = :userid")
    Review findByUser(@Param("wineid") Long wineId, @Param("userid") Integer userId);

}
