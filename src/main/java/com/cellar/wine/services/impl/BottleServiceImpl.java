/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services.impl;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.repositories.BottleRepository;
import com.cellar.wine.services.BottleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BottleServiceImpl implements BottleService {

    @Inject
    private BottleRepository bottleRepository;

    public Bottle findByUser(Integer userId, Long id) {
        return bottleRepository.findByUser(userId, id);
    }

    public Bottle findByWine(Integer userId, Long wineId) {
        return bottleRepository.findByWine(userId, wineId);
    }

    @Override
    public Bottle findById(Long aLong) {
        return bottleRepository.findById(aLong).orElse(null);
    }

    @Override
    public Bottle save(Bottle object) {
        return bottleRepository.save(object);
    }

    @Override
    public void delete(Bottle object) {
        bottleRepository.delete(object);
    }

}
