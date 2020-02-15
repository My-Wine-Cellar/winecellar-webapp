/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Wishlist;

/**
 * Wishlist service
 */
public interface WishlistService extends CrudService<Wishlist, Long> {

    /**
     * Find by user
     * @param userId The user identifier
     * @param id The wishlist identifier
     * @return The wishlist
     */
    Wishlist findByUser(Integer userId, Long id);

    /**
     * Find by wine
     * @param userId The user identifier
     * @param wineId The wine identifier
     * @return The wishlist
     */
    Wishlist findByWine(Integer userId, Long wineId);

}
