/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.GenericTastingNotes;

/**
 * Tasting notes service
 */
public interface TastingNotesService extends CrudService<GenericTastingNotes, Long> {

    /**
     * Find by user
     * @param userId The user identifier
     * @param id The tasting notes identifier
     * @return The tasting notes
     */
    GenericTastingNotes findByUser(Integer userId, Long id);

    /**
     * Find by wine
     * @param userId The user identifier
     * @param wineId The wine identifier
     * @return The tasting notes
     */
    GenericTastingNotes findByWine(Integer userId, Long wineId);
}
