package com.cellar.wine.services.impl;

import com.cellar.wine.models.Region;
import com.cellar.wine.repositories.RegionRepository;
import com.cellar.wine.services.RegionService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Set<Region> findAll() {
        Set<Region> regions = new TreeSet<>();
        regionRepository.findAll().forEach(regions::add);
        return regions;
    }

    @Override
    public Region findById(Long aLong) {
        return regionRepository.findById(aLong).orElse(null);
    }

    @Override
    public Region save(Region object) {
        return regionRepository.save(object);
    }

    @Override
    public void delete(Region object) {
        regionRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        regionRepository.deleteById(aLong);
    }

    @Override
    public Region findByLowerCaseName(String lcName, Long countryId) {
        return regionRepository.findByLowerCaseName(lcName, countryId);
    }
}
