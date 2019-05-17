package com.cellar.wine.services.impl;

import com.cellar.wine.models.Grape;
import com.cellar.wine.repositories.GrapeRepository;
import com.cellar.wine.services.GrapeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GrapeServiceImpl implements GrapeService {

    private final GrapeRepository grapeRepository;

    public GrapeServiceImpl(GrapeRepository grapeRepository) {
        this.grapeRepository = grapeRepository;
    }

    @Override
    public Grape findGrapeByName(String name) {
        return grapeRepository.findGrapeByName(name);
    }

    @Override
    public Set<Grape> findAll() {
        Set<Grape> grapes = new HashSet<>();
        grapeRepository.findAll().forEach(grapes::add);
        return grapes;
    }

    @Override
    public Grape findById(Long aLong) {
        return grapeRepository.findById(aLong).orElse(null);
    }

    @Override
    public Grape save(Grape object) {
        return grapeRepository.save(object);
    }

    @Override
    public void delete(Grape object) {
        grapeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        grapeRepository.deleteById(aLong);
    }
}
