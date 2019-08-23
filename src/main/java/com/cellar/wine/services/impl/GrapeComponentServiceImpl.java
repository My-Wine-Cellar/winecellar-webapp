package com.cellar.wine.services.impl;

import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.repositories.GrapeComponentRepository;
import com.cellar.wine.repositories.GrapeRepository;
import com.cellar.wine.services.GrapeComponentService;
import com.cellar.wine.services.GrapeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.TreeSet;

@Service
public class GrapeComponentServiceImpl implements GrapeComponentService {

    @Inject
    private GrapeComponentRepository grapeRepository;

    @Override
    public Set<GrapeComponent> findAll() {
        Set<GrapeComponent> grapes = new TreeSet<>();
        grapeRepository.findAll().forEach(grapes::add);
        return grapes;
    }

    @Override
    public GrapeComponent findById(Long aLong) {
        return grapeRepository.findById(aLong).orElse(null);
    }

    @Override
    public GrapeComponent save(GrapeComponent object) {
        return grapeRepository.save(object);
    }

    @Override
    public Iterable<GrapeComponent> saveAll(Iterable<GrapeComponent> grapes) {
        return grapeRepository.saveAll(grapes);
    }

    @Override
    public void delete(GrapeComponent object) {
        grapeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        grapeRepository.deleteById(aLong);
    }
}
