/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Tasted;
import info.mywinecellar.repository.TastedRepository;
import info.mywinecellar.service.TastedService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Tasted service implementation
 */
@Service
public class TastedServiceImpl implements TastedService {

    @Inject
    private TastedRepository tastedRepository;

    @Override
    public Tasted findByUser(Integer userId, Long id) {
        return tastedRepository.findByUser(userId, id);
    }

    @Override
    public Tasted findByWine(Integer userId, Long wineId) {
        return tastedRepository.findByWine(userId, wineId);
    }

    @Override
    public Tasted findById(Long aLong) {
        return tastedRepository.findById(aLong).orElse(null);
    }

    @Override
    public Tasted save(Tasted object) {
        return tastedRepository.save(object);
    }

    @Override
    public void delete(Tasted object) {
        tastedRepository.delete(object);
    }

}
