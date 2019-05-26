package com.cellar.wine.repositories;

import com.cellar.wine.models.Wine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WineRepository extends CrudRepository<Wine, Long> {

    Wine findByName(String name);

    List<Wine> findAllByName(String name);

}
