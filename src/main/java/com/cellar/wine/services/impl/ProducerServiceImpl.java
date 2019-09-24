package com.cellar.wine.services.impl;

import com.cellar.wine.models.Producer;
import com.cellar.wine.repositories.ProducerRepository;
import com.cellar.wine.services.ProducerService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Inject
    private ProducerRepository producerRepository;

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
    public Producer findByName(String name) {
        return producerRepository.findByName(name);
    }

}
