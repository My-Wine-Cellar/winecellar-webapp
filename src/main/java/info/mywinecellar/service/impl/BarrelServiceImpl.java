/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Barrel;
import info.mywinecellar.repository.BarrelRepository;
import info.mywinecellar.service.BarrelService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Barrel service implementation
 */
@Service
public class BarrelServiceImpl implements BarrelService {

    @Inject
    private BarrelRepository barrelRepository;

    @Override
    public List<Barrel> findAll() {
        List<Barrel> barrels = new ArrayList<>();
        barrelRepository.findAll().forEach(barrels::add);
        return barrels;
    }

    @Override
    public Barrel findById(Long aLong) {
        return barrelRepository.findById(aLong).orElse(null);
    }

    @Override
    public Barrel save(Barrel object) {
        return barrelRepository.save(object);
    }

    @Override
    public void delete(Barrel object) {
        barrelRepository.delete(object);
    }

    @Override
    public List<Barrel> findByLowerCaseName(String lcName) {
        return barrelRepository.findByLowerCaseName(lcName);
    }
}
