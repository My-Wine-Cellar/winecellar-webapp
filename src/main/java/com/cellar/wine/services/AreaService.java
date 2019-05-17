package com.cellar.wine.services;

import com.cellar.wine.models.Area;

public interface AreaService extends CrudService<Area, Long> {

    Area findByName(String name);
}
