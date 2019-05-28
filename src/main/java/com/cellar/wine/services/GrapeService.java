package com.cellar.wine.services;

import com.cellar.wine.models.Grape;

import java.util.Set;

public interface GrapeService extends CrudService<Grape, Long> {

    Grape findByName(String name);

    Set<Grape> getWhiteGrapes();

    Set<Grape> getRedGrapes();
}
