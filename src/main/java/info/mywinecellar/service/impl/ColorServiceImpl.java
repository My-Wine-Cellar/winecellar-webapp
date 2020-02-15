/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Color;
import info.mywinecellar.repository.ColorRepository;
import info.mywinecellar.service.ColorService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


@Service
public class ColorServiceImpl implements ColorService {

    @Inject
    ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        colorRepository.findAll().forEach(colors::add);
        return colors;
    }

    @Override
    public Color findById(Long aLong) {
        return colorRepository.findById(aLong).orElse(null);
    }

    @Override
    public Color save(Color object) {
        return colorRepository.save(object);
    }

    @Override
    public void delete(Color object) {
        colorRepository.delete(object);
    }
}
