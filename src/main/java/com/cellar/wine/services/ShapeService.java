package com.cellar.wine.services;

import com.cellar.wine.models.Shape;

import javax.persistence.OrderBy;
import java.util.List;

public interface ShapeService extends CrudService<Shape, Long> {

    @OrderBy
    List<Shape> findAll();
}
