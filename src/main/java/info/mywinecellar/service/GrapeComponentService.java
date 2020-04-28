/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.GrapeComponent;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

/**
 * GrapeComponent service
 */
@Component
public class GrapeComponentService {

    @Inject
    EntityManager em;

    /**
     * Save all
     *
     * @param grapes The grape components
     */
    @Transactional
    public void saveAll(Iterable<GrapeComponent> grapes) {
        try {
            grapes.forEach(grape -> em.persist(grape));
        } catch (Exception e) {
            System.out.println(grapes.getClass());
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

}
