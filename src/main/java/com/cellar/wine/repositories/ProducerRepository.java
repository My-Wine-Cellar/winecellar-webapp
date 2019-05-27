package com.cellar.wine.repositories;

import com.cellar.wine.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);

}
