/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Area;
import info.mywinecellar.repository.AreaRepository;
import info.mywinecellar.service.AreaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AreaServiceImpl implements AreaService {

    @Inject
    private AreaRepository areaRepository;

    @Override
    public Area findById(Long aLong) {
        return areaRepository.findById(aLong).orElse(null);
    }

    @Override
    public Area save(Area object) {
        return areaRepository.save(object);
    }

    @Override
    public void delete(Area object) {
        areaRepository.delete(object);
    }

    @Override
    public Area findByName(String name) {
        return areaRepository.findByName(name);
    }
}
