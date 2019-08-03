package com.cellar.wine.services.impl;

import com.cellar.wine.models.Barrel;
import com.cellar.wine.repositories.BarrelRepository;
import com.cellar.wine.services.BarrelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class BarrelServiceImpl implements BarrelService {

    private final BarrelRepository barrelRepository;

    public BarrelServiceImpl(BarrelRepository barrelRepository) {
        this.barrelRepository = barrelRepository;
    }

    @Override
    public Set<Barrel> findAll() {
        Set<Barrel> barrels = new TreeSet<>();
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
    public void deleteById(Long aLong) {
        barrelRepository.deleteById(aLong);
    }

    @Override
    public List<Barrel> findByLowerCaseName(String lcName) {
        return barrelRepository.findByLowerCaseName(lcName);
    }
}
