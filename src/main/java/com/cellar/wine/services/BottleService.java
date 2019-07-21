package com.cellar.wine.services;

import com.cellar.wine.models.Bottle;

public interface BottleService extends CrudService<Bottle, Long> {

    Bottle findByUser(Long wineId, Integer userId);

}
