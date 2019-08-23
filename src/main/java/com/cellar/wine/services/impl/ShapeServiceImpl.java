package com.cellar.wine.services.impl;

import com.cellar.wine.models.Shape;
import com.cellar.wine.repositories.ShapeRepository;
import com.cellar.wine.services.ShapeService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class ShapeServiceImpl implements ShapeService {

    private final ShapeRepository shapeRepository;

    public ShapeServiceImpl(ShapeRepository shapeRepository) {
        this.shapeRepository = shapeRepository;
    }

    @Override
    public Set<Shape> findAll() {
        Set<Shape> shapes = new TreeSet<>();
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

    @Override
    public void deleteById(Long aLong) {
        shapeRepository.deleteById(aLong);
    }
}
