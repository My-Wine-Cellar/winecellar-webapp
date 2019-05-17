package com.cellar.wine.services.impl;

import com.cellar.wine.models.Area;
import com.cellar.wine.repositories.AreaRepository;
import com.cellar.wine.services.AreaService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public Set<Area> findAll() {
        Set<Area> areas = new HashSet<>();
        areaRepository.findAll().addAll(areas);
        return areas;
    }

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
    public void deleteById(Long aLong) {
        areaRepository.deleteById(aLong);
    }

    @Override
    public Area findByName(String name) {
        return areaRepository.findByName(name);
    }
}
