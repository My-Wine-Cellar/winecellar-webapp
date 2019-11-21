/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Type;
import info.mywinecellar.repository.TypeRepository;
import info.mywinecellar.service.TypeService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Inject
    TypeRepository typeRepository;

    @Override
    public List<Type> findAll() {
        List<Type> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public Type findById(Long aLong) {
        return typeRepository.findById(aLong).orElse(null);
    }

    @Override
    public Type save(Type object) {
        return typeRepository.save(object);
    }

    @Override
    public void delete(Type object) {
        typeRepository.delete(object);
    }
}
