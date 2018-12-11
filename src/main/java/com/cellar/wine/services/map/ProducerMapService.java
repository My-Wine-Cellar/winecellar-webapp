package com.cellar.wine.services.map;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class ProducerMapService extends AbstractMapService<Producer, Long> implements ProducerService {

    private final WineService wineService;

    public ProducerMapService(WineService wineService) {
        this.wineService = wineService;
    }

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
        if (object != null) {
            if (object.getWines() != null) {
                object.getWines().forEach(wine -> {
                    if (wine.getId() == null) {
                        Wine savedWine = wineService.save(wine);
                        wine.setId(savedWine.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
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
