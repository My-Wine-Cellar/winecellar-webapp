/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Maceration;
import info.mywinecellar.repository.MacerationRepository;
import info.mywinecellar.service.MacerationService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Maceration service implementation
 */
@Service
public class MacerationServiceImpl implements MacerationService {

    @Inject
    private MacerationRepository macerationRepository;

    @Override
    public Maceration findById(Long aLong) {
        return null;
    }

    @Override
    public Maceration save(Maceration object) {
        return macerationRepository.save(object);
    }

    @Override
    public void delete(Maceration object) {

    }

}
