package com.cellar.wine.services.impl;

import com.cellar.wine.models.Barrel;
import com.cellar.wine.repositories.BarrelRepository;
import com.cellar.wine.services.BarrelService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
