package com.cellar.wine.services;

import com.cellar.wine.models.Wine;

import java.util.List;

public interface WineService extends CrudService<Wine, Long> {

    Wine findByName(String name);

    List<Wine> findAllByName(String name);
}
