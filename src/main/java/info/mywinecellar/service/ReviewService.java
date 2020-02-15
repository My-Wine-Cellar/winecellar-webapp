/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Review;

/**
 * Review service
 */
public interface ReviewService extends CrudService<Review, Long> {

    /**
     * Find by user
     * @param userId The user identifier
     * @param id The review identifier
     * @return The review
     */
    Review findByUser(Integer userId, Long id);

    /**
     * Find by wine
     * @param userId The user identifier
     * @param wineId The wine identifier
     * @return The review
     */
    Review findByWine(Integer userId, Long wineId);
}
