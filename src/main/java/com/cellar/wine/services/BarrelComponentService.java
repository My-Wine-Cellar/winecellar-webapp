package com.cellar.wine.services;

import com.cellar.wine.models.BarrelComponent;

public interface BarrelComponentService extends CrudService<BarrelComponent, Long> {

    Iterable<BarrelComponent> saveAll(Iterable<BarrelComponent> barrels);
}
