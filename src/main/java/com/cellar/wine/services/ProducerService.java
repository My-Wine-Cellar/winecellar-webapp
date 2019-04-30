package com.cellar.wine.services;

import com.cellar.wine.models.Producer;

import java.util.Collection;

public interface ProducerService extends CrudService<Producer, Long> {

    Producer findByName(String name);

    Collection<Producer> searchProducersByName(String search);
}
