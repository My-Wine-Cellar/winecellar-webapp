/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Producer;
import info.mywinecellar.repository.ProducerRepository;
import info.mywinecellar.service.ProducerService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Producer service implementation
 */
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
    public void update(Producer model, Producer save) {
        save.setName(model.getName());
        save.setDescription(model.getDescription());
        save.setEmail(model.getEmail());
        save.setWebsite(model.getWebsite());
        save.setPhone(model.getPhone());
        save.setFax(model.getFax());
        producerRepository.save(save);
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
