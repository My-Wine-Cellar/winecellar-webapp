package com.cellar.wine.services.impl;


import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.WineRepository;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public Set<Wine> findAll() {
        Set<Wine> wines = new TreeSet<>();
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

    @Override
    public Wine findByName(String name) {
        return wineRepository.findByName(name);
    }

    @Override
    public List<Wine> findAllByName(String name) {
        return wineRepository.findAllByName(name);
    }
}
