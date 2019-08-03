package com.cellar.wine.repositories;

import com.cellar.wine.models.Barrel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BarrelRepository extends JpaRepository<Barrel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM barrel b WHERE lower(b.name) LIKE :lc_name ORDER BY b.id")
    List<Barrel> findByLowerCaseName(@Param("lc_name") String lcName);
}
