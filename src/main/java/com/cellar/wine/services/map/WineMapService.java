package com.cellar.wine.services.map;

import com.cellar.wine.models.Wine;
import com.cellar.wine.services.WineService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class WineMapService extends AbstractMapService<Wine, Long> implements WineService {

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
        return super.save(object);
    }

    @Override
    public void delete(Wine object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Wine findByName(String name) {
        return null;
    }
}
