package com.cellar.wine.services.map;

import com.cellar.wine.model.Producer;
import com.cellar.wine.services.ProducerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class ProducerServiceMap extends AbstractMapService<Producer, Long> implements ProducerService {

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
        return super.save(object);
    }

    @Override
    public void delete(Producer object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Producer findByName(String name) {
        return null;
    }
}
