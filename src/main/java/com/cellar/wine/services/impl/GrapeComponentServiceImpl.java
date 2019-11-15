/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services.impl;

import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.repositories.GrapeComponentRepository;
import com.cellar.wine.services.GrapeComponentService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class GrapeComponentServiceImpl implements GrapeComponentService {

    @Inject
    private GrapeComponentRepository grapeRepository;

    @Override
    public GrapeComponent findById(Long aLong) {
        return grapeRepository.findById(aLong).orElse(null);
    }

    @Override
    public GrapeComponent save(GrapeComponent object) {
        return grapeRepository.save(object);
    }

    @Override
    public Iterable<GrapeComponent> saveAll(Iterable<GrapeComponent> grapes) {
        return grapeRepository.saveAll(grapes);
    }

    @Override
    public void delete(GrapeComponent object) {
        grapeRepository.delete(object);
    }

}
