package com.cellar.wine.services.impl;

import com.cellar.wine.models.Maceration;
import com.cellar.wine.repositories.MacerationRepository;
import com.cellar.wine.services.MacerationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MacerationServiceImpl implements MacerationService {

    @Inject
    private MacerationRepository macerationRepository;

    @Override
    public Maceration findById(Long aLong) {
        return null;
    }

    @Override
    public Maceration save(Maceration object) {
        return macerationRepository.save(object);
    }

    @Override
    public void delete(Maceration object) {

    }

}
