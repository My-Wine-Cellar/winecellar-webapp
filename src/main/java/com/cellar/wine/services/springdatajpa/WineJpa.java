package com.cellar.wine.services.springdatajpa;


import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.WineRepository;
import com.cellar.wine.services.WineService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class WineJpa implements WineService {

    private final WineRepository wineRepository;

    public WineJpa(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public Wine findByName(String name) {
        return wineRepository.findByName(name);
    }

    @Override
    public Set<Wine> findAll() {
        Set<Wine> wines = new HashSet<>();
        wineRepository.findAll().forEach(wines::add);
        return wines;
    }

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
    public void deleteById(Long aLong) {
        wineRepository.deleteById(aLong);
    }
}
