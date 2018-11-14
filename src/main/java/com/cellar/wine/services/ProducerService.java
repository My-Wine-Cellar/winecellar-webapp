package com.cellar.wine.services;

import com.cellar.wine.model.Producer;

import java.util.List;

public interface ProducerService extends CrudService<Producer, Long> {

    Producer findByName(String name);
}
