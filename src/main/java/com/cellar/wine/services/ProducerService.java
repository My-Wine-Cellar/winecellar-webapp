package com.cellar.wine.services;

import com.cellar.wine.models.Producer;

import java.util.List;

public interface ProducerService extends CrudService<Producer, Long> {

    Producer findByName(String name);

    List<Producer> findAllByNameLike(String name);
}
