package com.cellar.wine.services.impl;

import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.repositories.TastingNotesRepository;
import com.cellar.wine.services.TastingNotesService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class TastingNotesServiceImpl implements TastingNotesService {

    private TastingNotesRepository tastingNotesRepository;

    public TastingNotesServiceImpl(TastingNotesRepository tastingNotesRepository) {
        this.tastingNotesRepository = tastingNotesRepository;
    }

    @Override
    public Set<GenericTastingNotes> findAll() {
        Set<GenericTastingNotes> tastingNotes = new TreeSet<>();
        tastingNotesRepository.findAll().forEach(tastingNotes::add);
        return tastingNotes;
    }

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

    @Override
    public void deleteById(Long aLong) {
        tastingNotesRepository.deleteById(aLong);
    }
}
