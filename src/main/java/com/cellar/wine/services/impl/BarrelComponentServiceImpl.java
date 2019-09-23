package com.cellar.wine.services.impl;

import com.cellar.wine.models.BarrelComponent;
import com.cellar.wine.repositories.BarrelComponentRepository;
import com.cellar.wine.services.BarrelComponentService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.TreeSet;

@Service
public class BarrelComponentServiceImpl implements BarrelComponentService {

    @Inject
    private BarrelComponentRepository barrelComponentRepository;

    @Override
    public Set<BarrelComponent> findAll() {
        Set<BarrelComponent> barrelComponents = new TreeSet<>();
        barrelComponentRepository.findAll().forEach(barrelComponents::add);
        return barrelComponents;
    }

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

    @Override
    public void deleteById(Long aLong) {
        barrelComponentRepository.deleteById(aLong);
    }
}
