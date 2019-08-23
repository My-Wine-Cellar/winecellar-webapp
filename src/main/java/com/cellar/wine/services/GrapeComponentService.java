package com.cellar.wine.services;

import com.cellar.wine.models.GrapeComponent;

public interface GrapeComponentService extends CrudService<GrapeComponent, Long> {

    Iterable<GrapeComponent> saveAll(Iterable<GrapeComponent> grapes);
}
