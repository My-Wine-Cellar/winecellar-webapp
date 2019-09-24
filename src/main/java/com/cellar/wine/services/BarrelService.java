package com.cellar.wine.services;

import com.cellar.wine.models.Barrel;

import java.util.List;

public interface BarrelService extends CrudService<Barrel, Long> {

    List<Barrel> findByLowerCaseName(String lcName);

    List<Barrel> findAll();
}
