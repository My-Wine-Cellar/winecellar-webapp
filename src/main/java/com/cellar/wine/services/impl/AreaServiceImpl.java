package com.cellar.wine.services.impl;

import com.cellar.wine.models.Area;
import com.cellar.wine.repositories.AreaRepository;
import com.cellar.wine.services.AreaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AreaServiceImpl implements AreaService {

    @Inject
    private AreaRepository areaRepository;

    @Override
    public Area findById(Long aLong) {
        return areaRepository.findById(aLong).orElse(null);
    }

    @Override
    public Area save(Area object) {
        return areaRepository.save(object);
    }

    @Override
    public void delete(Area object) {
        areaRepository.delete(object);
    }

    @Override
    public Area findByName(String name) {
        return areaRepository.findByName(name);
    }
}
