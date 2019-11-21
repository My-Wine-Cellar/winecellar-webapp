/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;


import info.mywinecellar.model.Wine;
import info.mywinecellar.repository.WineRepository;
import info.mywinecellar.service.WineService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Wine service implementation
 */
@Service
public class WineServiceImpl implements WineService {

    @Inject
    private WineRepository wineRepository;

    @Override
    public Wine findById(Long aLong) {
        return wineRepository.findById(aLong).orElse(null);
    }

    @Override
    public Wine save(Wine object) {
        return wineRepository.save(object);
    }

    @Override
    public void delete(Wine object) {
        wineRepository.delete(object);
    }

    @Override
    public Wine findByName(String name) {
        return wineRepository.findByName(name);
    }

    @Override
    public List<Wine> findAllByName(String name) {
        return wineRepository.findAllByName(name);
    }
}
