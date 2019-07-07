package com.cellar.wine.services.impl;

import com.cellar.wine.models.Tasted;
import com.cellar.wine.repositories.TastedRepository;
import com.cellar.wine.services.TastedService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class TastedServiceImpl implements TastedService {

    private TastedRepository tastedRepository;

    public TastedServiceImpl(TastedRepository tastedRepository) {
        this.tastedRepository = tastedRepository;
    }

    @Override
    public Set<Tasted> findAll() {
        Set<Tasted> tasteds = new TreeSet<>();
        tastedRepository.findAll().forEach(tasteds::add);
        return tasteds;
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

    @Override
    public void deleteById(Long aLong) {
        tastedRepository.deleteById(aLong);
    }

}
