/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Region;
import info.mywinecellar.repository.RegionRepository;
import info.mywinecellar.service.RegionService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Region service implementation
 */
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
