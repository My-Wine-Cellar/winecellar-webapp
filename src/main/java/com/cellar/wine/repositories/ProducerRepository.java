package com.cellar.wine.repositories;

import com.cellar.wine.model.Producer;
import org.springframework.data.repository.CrudRepository;

public interface ProducerRepository extends CrudRepository<Producer, Long> {
}
