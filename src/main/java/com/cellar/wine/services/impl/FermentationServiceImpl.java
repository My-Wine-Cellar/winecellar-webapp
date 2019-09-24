package com.cellar.wine.services.impl;

import com.cellar.wine.models.Fermentation;
import com.cellar.wine.repositories.FermentationRepository;
import com.cellar.wine.services.FermentationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class FermentationServiceImpl implements FermentationService {

    @Inject
    private FermentationRepository fermentationRepository;

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

}
