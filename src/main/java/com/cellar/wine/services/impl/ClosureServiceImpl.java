/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services.impl;

import com.cellar.wine.models.Closure;
import com.cellar.wine.repositories.ClosureRepository;
import com.cellar.wine.services.ClosureService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClosureServiceImpl implements ClosureService {

    @Inject
    private ClosureRepository closureRepository;

    @Override
    public List<Closure> findAll() {
        List<Closure> closures = new ArrayList<>();
        closureRepository.findAll().forEach(closures::add);
        return closures;
    }

    @Override
    public Closure findById(Long aLong) {
        return closureRepository.findById(aLong).orElse(null);
    }

    @Override
    public Closure save(Closure object) {
        return closureRepository.save(object);
    }

    @Override
    public void delete(Closure object) {
        closureRepository.delete(object);
    }

}
