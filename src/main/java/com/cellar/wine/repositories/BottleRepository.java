package com.cellar.wine.repositories;

import com.cellar.wine.models.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BottleRepository extends JpaRepository<Bottle, Long> {

    @Query("SELECT b FROM Bottle b WHERE b.wine.id = :wineid AND b.user.id = :userid")
    Bottle findByUser(@Param("wineid") Long wineId, @Param("userid") Integer userId);
}
