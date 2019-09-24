package com.cellar.wine.services;

import com.cellar.wine.models.Barrel;

import javax.persistence.OrderBy;
import java.util.List;

public interface BarrelService extends CrudService<Barrel, Long> {

    List<Barrel> findByLowerCaseName(String lcName);

    @OrderBy
    List<Barrel> findAll();
}
