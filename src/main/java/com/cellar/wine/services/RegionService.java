package com.cellar.wine.services;

import com.cellar.wine.models.Region;

public interface RegionService extends CrudService<Region, Long> {

    Region findByName(String name);
}
