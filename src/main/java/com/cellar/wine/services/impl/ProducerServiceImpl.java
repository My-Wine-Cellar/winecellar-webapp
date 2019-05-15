package com.cellar.wine.services.impl;

import com.cellar.wine.models.Producer;
import com.cellar.wine.repositories.ProducerRepository;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Set<Producer> findAll() {
        Set<Producer> producers = new HashSet<>();
        producerRepository.findAll().forEach(producers::add);
        return producers;
    }

    @Override
    public Producer findById(Long aLong) {
        return producerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Producer save(Producer object) {
        return producerRepository.save(object);
    }

    @Override
    public void delete(Producer object) {
        producerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        producerRepository.deleteById(aLong);
    }

    @Override
    public Producer findByName(String name) {
        return producerRepository.findByName(name);
    }

}
