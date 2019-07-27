package com.cellar.wine.repositories;

import com.cellar.wine.models.Tasted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TastedRepository extends JpaRepository<Tasted, Long> {

    @Query("SELECT t FROM Tasted t WHERE t.user.id = :userid AND t.id = :id")
    Tasted findByUser(@Param("userid") Integer userId, @Param("id") Long id);

    @Query("SELECT t FROM Tasted t WHERE t.user.id = :userid AND t.wine.id = :wineid")
    Tasted findByWine(@Param("userid") Integer userId, @Param("wineid") Long wineId);

}
