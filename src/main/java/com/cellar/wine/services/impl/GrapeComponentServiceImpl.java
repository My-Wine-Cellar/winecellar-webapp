package com.cellar.wine.services.impl;

import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.repositories.GrapeComponentRepository;
import com.cellar.wine.services.GrapeComponentService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class GrapeComponentServiceImpl implements GrapeComponentService {

    @Inject
    private GrapeComponentRepository grapeRepository;

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

}
