package com.cellar.wine.repositories;

import com.cellar.wine.model.Wine;
import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Long> {

    Wine findByName(String name);
}
