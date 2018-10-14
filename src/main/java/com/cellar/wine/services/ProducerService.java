package com.cellar.wine.services;

import com.cellar.wine.model.Producer;

import java.util.Set;

public interface ProducerService {

    Producer findById(Long id);
    Producer save(Producer producer);
    Set<Producer> findAll();
}
