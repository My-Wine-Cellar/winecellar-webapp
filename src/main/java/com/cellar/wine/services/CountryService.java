package com.cellar.wine.services;

import com.cellar.wine.models.Country;

public interface CountryService extends CrudService<Country, Long> {

    Country findByName(String name);
}
