package com.cellar.wine.repositories;

import com.cellar.wine.models.Tasted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TastedRepository extends JpaRepository<Tasted, Long> {

    @Query("SELECT t FROM Tasted t WHERE t.wine.id = :wineid AND t.user.id = :userid")
    Tasted findByUser(@Param("wineid") Long wineId, @Param("userid") Integer userId);

}
