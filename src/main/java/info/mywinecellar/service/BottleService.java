/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Bottle;

/**
 * Bottle service
 */
public interface BottleService extends CrudService<Bottle, Long> {

    /**
     * Find by user
     * @param userId The user identifier
     * @param id The bottle identifier
     * @return The bottle
     */
    Bottle findByUser(Integer userId, Long id);

    /**
     * Find by wine
     * @param userId The user identifier
     * @param wineId The wine identifier
     * @return The bottle
     */
    Bottle findByWine(Integer userId, Long wineId);

}
