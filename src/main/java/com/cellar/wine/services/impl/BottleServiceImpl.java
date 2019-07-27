package com.cellar.wine.services.impl;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.repositories.BottleRepository;
import com.cellar.wine.services.BottleService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class BottleServiceImpl implements BottleService {

    private BottleRepository bottleRepository;

    public BottleServiceImpl(BottleRepository bottleRepository) {
        this.bottleRepository = bottleRepository;
    }

    public Bottle findByUser(Integer userId, Long id) {
        return bottleRepository.findByUser(userId, id);
    }

    public Bottle findByWine(Integer userId, Long wineId) {
        return bottleRepository.findByWine(userId, wineId);
    }

    @Override
    public Set<Bottle> findAll() {
        Set<Bottle> bottles = new TreeSet<>();
        bottleRepository.findAll().forEach(bottles::add);
        return bottles;
    }

    @Override
    public Bottle findById(Long aLong) {
        return bottleRepository.findById(aLong).orElse(null);
    }

    @Override
    public Bottle save(Bottle object) {
        return bottleRepository.save(object);
    }

    @Override
    public void delete(Bottle object) {
        bottleRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        bottleRepository.deleteById(aLong);
    }

}
