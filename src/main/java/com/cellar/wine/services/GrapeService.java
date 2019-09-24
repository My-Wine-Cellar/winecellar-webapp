package com.cellar.wine.services;

import com.cellar.wine.models.Grape;

import javax.persistence.OrderBy;
import java.util.List;

public interface GrapeService extends CrudService<Grape, Long> {

    Grape findByName(String name);

    List<Grape> getWhiteGrapes();

    List<Grape> getRedGrapes();

    @OrderBy
    List<Grape> findAll();

    Grape findByLowerCaseName(String lcName);
}
