/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Fermentation;
import info.mywinecellar.repository.FermentationRepository;
import info.mywinecellar.service.FermentationService;
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
