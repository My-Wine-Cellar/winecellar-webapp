/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services.impl;

import com.cellar.wine.models.Tasted;
import com.cellar.wine.repositories.TastedRepository;
import com.cellar.wine.services.TastedService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TastedServiceImpl implements TastedService {

    @Inject
    private TastedRepository tastedRepository;

    public Tasted findByUser(Integer userId, Long id) {
        return tastedRepository.findByUser(userId, id);
    }

    public Tasted findByWine(Integer userId, Long wineId) {
        return tastedRepository.findByWine(userId, wineId);
    }

    @Override
    public Tasted findById(Long aLong) {
        return tastedRepository.findById(aLong).orElse(null);
    }

    @Override
    public Tasted save(Tasted object) {
        return tastedRepository.save(object);
    }

    @Override
    public void delete(Tasted object) {
        tastedRepository.delete(object);
    }

}
