package com.cellar.wine.services.impl;

import com.cellar.wine.models.Grape;
import com.cellar.wine.repositories.GrapeRepository;
import com.cellar.wine.services.GrapeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrapeServiceImpl implements GrapeService {

    @Inject
    private GrapeRepository grapeRepository;

    @Override
    public Grape findByName(String name) {
        return grapeRepository.findByName(name);
    }

    @Override
    public List<Grape> findAll() {
        List<Grape> grapes = new ArrayList<>();
        grapeRepository.findAll(Sort.by("name")).forEach(grapes::add);
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
    public List<Grape> getWhiteGrapes() {
        return grapeRepository.getWhiteGrapes();
    }

    @Override
    public List<Grape> getRedGrapes() {
        return grapeRepository.getRedGrapes();
    }

    @Override
    public Grape findByLowerCaseName(String lcName) {
        return grapeRepository.findByLowerCaseName(lcName);
    }
}
