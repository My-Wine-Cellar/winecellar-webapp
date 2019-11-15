/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services.impl;

import com.cellar.wine.models.Shape;
import com.cellar.wine.repositories.ShapeRepository;
import com.cellar.wine.services.ShapeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShapeServiceImpl implements ShapeService {

    @Inject
    private ShapeRepository shapeRepository;

    @Override
    public List<Shape> findAll() {
        List<Shape> shapes = new ArrayList<>();
        shapeRepository.findAll().forEach(shapes::add);
        return shapes;
    }

    @Override
    public Shape findById(Long aLong) {
        return shapeRepository.findById(aLong).orElse(null);
    }

    @Override
    public Shape save(Shape object) {
        return shapeRepository.save(object);
    }

    @Override
    public void delete(Shape object) {
        shapeRepository.delete(object);
    }

}
