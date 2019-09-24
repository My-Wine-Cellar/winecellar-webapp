package com.cellar.wine.services.impl;


import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.WineRepository;
import com.cellar.wine.services.WineService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

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
