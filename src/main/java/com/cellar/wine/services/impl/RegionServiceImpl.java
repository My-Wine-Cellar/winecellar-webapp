package com.cellar.wine.services.impl;

import com.cellar.wine.models.Region;
import com.cellar.wine.repositories.RegionRepository;
import com.cellar.wine.services.RegionService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RegionServiceImpl implements RegionService {

    @Inject
    private RegionRepository regionRepository;

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
    public Region findByLowerCaseName(String lcName, Long countryId) {
        return regionRepository.findByLowerCaseName(lcName, countryId);
    }
}
