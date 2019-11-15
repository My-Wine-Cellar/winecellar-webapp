/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.services;

import com.cellar.wine.models.Wishlist;

public interface WishlistService extends CrudService<Wishlist, Long> {

    Wishlist findByUser(Integer userId, Long id);
    Wishlist findByWine(Integer userId, Long wineId);

}
