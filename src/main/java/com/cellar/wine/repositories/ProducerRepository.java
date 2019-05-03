package com.cellar.wine.repositories;

import com.cellar.wine.models.Producer;
import org.springframework.data.repository.CrudRepository;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    Producer findByName(String name);

}
