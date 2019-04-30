package com.cellar.wine.repositories;

import com.cellar.wine.models.Producer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    Producer findByName(String name);

    Collection<Producer> searchProducersByName(String search);
}
