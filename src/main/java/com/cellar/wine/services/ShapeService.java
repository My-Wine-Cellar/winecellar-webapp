package com.cellar.wine.services;

import com.cellar.wine.models.Shape;

import java.util.List;

public interface ShapeService extends CrudService<Shape, Long> {

    List<Shape> findAll();
}
