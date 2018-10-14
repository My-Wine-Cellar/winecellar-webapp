package com.cellar.wine.services;

import com.cellar.wine.model.Wine;

import java.util.Set;

public interface WineService {

    Wine findById(Long id);
    Wine save(Wine wine);
    Set<Wine> findAll();
}
