package com.cellar.wine.services;

import com.cellar.wine.model.Wine;

import java.util.Set;

public interface WineService extends CrudService<Wine, Long> {

    Wine findByName(String name);
}
