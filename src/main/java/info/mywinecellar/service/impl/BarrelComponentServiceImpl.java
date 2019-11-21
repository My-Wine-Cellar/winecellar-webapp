/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.repository.BarrelComponentRepository;
import info.mywinecellar.service.BarrelComponentService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * BarrelComponent service implementation
 */
@Service
public class BarrelComponentServiceImpl implements BarrelComponentService {

    @Inject
    private BarrelComponentRepository barrelComponentRepository;

    @Override
    public BarrelComponent findById(Long aLong) {
        return barrelComponentRepository.findById(aLong).orElse(null);
    }

    @Override
    public BarrelComponent save(BarrelComponent object) {
        return barrelComponentRepository.save(object);
    }

    @Override
    public Iterable<BarrelComponent> saveAll(Iterable<BarrelComponent> barrels) {
        return barrelComponentRepository.saveAll(barrels);
    }

    @Override
    public void delete(BarrelComponent object) {
        barrelComponentRepository.delete(object);
    }

}
