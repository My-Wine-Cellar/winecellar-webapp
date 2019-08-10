package com.cellar.wine.services;

import com.cellar.wine.models.Country;

import java.util.List;

public interface CountryService extends CrudService<Country, Long> {

    Country findByLowerCaseName(String lcName);

    List<Country> findWithRegions();

}
