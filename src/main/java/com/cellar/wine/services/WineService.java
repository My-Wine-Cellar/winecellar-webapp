package com.cellar.wine.services;

import com.cellar.wine.model.Wine;

public interface WineService extends CrudService<Wine, Long> {

    Wine findByName(String name);
}
