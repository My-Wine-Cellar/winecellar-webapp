package com.cellar.wine.repositories;

import com.cellar.wine.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Long> {

    Wine findByName(String name);

    List<Wine> findAllByName(String name);

}
