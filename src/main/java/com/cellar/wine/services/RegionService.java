package com.cellar.wine.services;

import com.cellar.wine.models.Region;

public interface RegionService extends CrudService<Region, Long> {

    Region findByLowerCaseName(String lcName, Long countryId);
}
