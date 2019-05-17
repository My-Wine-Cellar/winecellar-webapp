package com.cellar.wine.services;

import com.cellar.wine.models.Grape;

public interface GrapeService extends CrudService<Grape, Long> {

    Grape findGrapeByName(String name);
}
