package com.cellar.wine.services.impl;

import com.cellar.wine.models.Maceration;
import com.cellar.wine.repositories.MacerationRepository;
import com.cellar.wine.services.MacerationService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MacerationServiceImpl implements MacerationService {

    private final MacerationRepository macerationRepository;

    public MacerationServiceImpl(MacerationRepository macerationRepository) {
        this.macerationRepository = macerationRepository;
    }

    @Override
    public Set<Maceration> findAll() {
        return null;
    }

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

    @Override
    public void deleteById(Long aLong) {

    }
}
