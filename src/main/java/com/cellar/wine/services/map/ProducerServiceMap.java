package com.cellar.wine.services.map;

import com.cellar.wine.model.Producer;
import com.cellar.wine.services.CrudService;

import java.util.Set;

public class ProducerServiceMap extends AbstractMapService<Producer, Long> implements CrudService<Producer, Long> {

    @Override
    public Set<Producer> findAll() {
        return super.findAll();
    }

    @Override
    public Producer findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Producer save(Producer object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Producer object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
