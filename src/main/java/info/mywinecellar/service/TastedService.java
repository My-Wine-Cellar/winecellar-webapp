/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Tasted;

/**
 * Tasted service
 */
public interface TastedService extends CrudService<Tasted, Long> {

    /**
     * Find by user
     * @param userId The user identifier
     * @param id The tasted identifier
     * @return The tasted
     */
    Tasted findByUser(Integer userId, Long id);

    /**
     * Find by wine
     * @param userId The user identifier
     * @param wineId The wine identifier
     * @return The tasted
     */
    Tasted findByWine(Integer userId, Long wineId);

}
