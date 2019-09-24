package com.cellar.wine.services.impl;

import com.cellar.wine.models.BarrelComponent;
import com.cellar.wine.repositories.BarrelComponentRepository;
import com.cellar.wine.services.BarrelComponentService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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
