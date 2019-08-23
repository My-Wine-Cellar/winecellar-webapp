package com.cellar.wine.services.impl;

import com.cellar.wine.models.Fermentation;
import com.cellar.wine.repositories.FermentationRepository;
import com.cellar.wine.services.FermentationService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FermentationServiceImpl implements FermentationService {

    private final FermentationRepository fermentationRepository;

    public FermentationServiceImpl(FermentationRepository fermentationRepository) {
        this.fermentationRepository = fermentationRepository;
    }

    @Override
    public Set<Fermentation> findAll() {
        return null;
    }

    @Override
    public Fermentation findById(Long aLong) {
        return null;
    }

    @Override
    public Fermentation save(Fermentation object) {
        return fermentationRepository.save(object);
    }

    @Override
    public void delete(Fermentation object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
