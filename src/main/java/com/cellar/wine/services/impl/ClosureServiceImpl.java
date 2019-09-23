package com.cellar.wine.services.impl;

import com.cellar.wine.models.Closure;
import com.cellar.wine.repositories.ClosureRepository;
import com.cellar.wine.services.ClosureService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class ClosureServiceImpl implements ClosureService {

    private final ClosureRepository closureRepository;

    public ClosureServiceImpl(ClosureRepository closureRepository) {
        this.closureRepository = closureRepository;
    }

    @Override
    public Set<Closure> findAll() {
        Set<Closure> closures = new TreeSet<>();
        closureRepository.findAll().forEach(closures::add);
        return closures;
    }

    @Override
    public Closure findById(Long aLong) {
        return closureRepository.findById(aLong).orElse(null);
    }

    @Override
    public Closure save(Closure object) {
        return closureRepository.save(object);
    }

    @Override
    public void delete(Closure object) {
        closureRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        closureRepository.deleteById(aLong);
    }
}
