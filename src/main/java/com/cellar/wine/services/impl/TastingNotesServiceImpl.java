/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services.impl;

import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.repositories.TastingNotesRepository;
import com.cellar.wine.services.TastingNotesService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TastingNotesServiceImpl implements TastingNotesService {

    @Inject
    private TastingNotesRepository tastingNotesRepository;

    @Override
    public GenericTastingNotes findById(Long aLong) {
        return tastingNotesRepository.findById(aLong).orElse(null);
    }

    @Override
    public GenericTastingNotes findByUser(Integer userId, Long id) {
        return tastingNotesRepository.findByUser(userId, id);
    }

    @Override
    public GenericTastingNotes findByWine(Integer userId, Long wineId) {
        return tastingNotesRepository.findByWine(userId, wineId);
    }

    @Override
    public GenericTastingNotes save(GenericTastingNotes object) {
        return tastingNotesRepository.save(object);
    }

    @Override
    public void delete(GenericTastingNotes object) {
        tastingNotesRepository.delete(object);
    }

}
