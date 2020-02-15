/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Country;
import info.mywinecellar.repository.CountryRepository;
import info.mywinecellar.service.CountryService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Country service implementation
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Inject
    private CountryRepository countryRepository;

    @Override
    public Country findById(Long aLong) {
        return countryRepository.findById(aLong).orElse(null);
    }

    @Override
    public Country save(Country object) {
        return countryRepository.save(object);
    }

    @Override
    public void delete(Country object) {
        countryRepository.delete(object);
    }

    @Override
    public Country findByLowerCaseName(String lcName) {
        return countryRepository.findByLowerCaseName(lcName);
    }

    @Override
    public List<Country> findWithRegions() {
        return countryRepository.findWithRegions();
    }
}
