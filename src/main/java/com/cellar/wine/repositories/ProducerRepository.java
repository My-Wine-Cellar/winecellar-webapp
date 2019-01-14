package com.cellar.wine.repositories;

import com.cellar.wine.models.Producer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    Producer findByName(String name);

    List<Producer> findAllByName(String name);
}
