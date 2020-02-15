/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Grape;
import info.mywinecellar.repository.GrapeRepository;
import info.mywinecellar.service.GrapeService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Grape service implementation
 */
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
