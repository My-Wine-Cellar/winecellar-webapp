package com.cellar.wine.services.map;

import com.cellar.wine.model.Wine;
import com.cellar.wine.services.CrudService;

import java.util.Set;

public class WineServiceMap extends AbstractMapService<Wine, Long> implements CrudService<Wine, Long> {

    @Override
    public Set<Wine> findAll() {
        return super.findAll();
    }

    @Override
    public Wine findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Wine save(Wine object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Wine object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
