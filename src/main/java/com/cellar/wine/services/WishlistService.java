package com.cellar.wine.services;

import com.cellar.wine.models.Wishlist;

public interface WishlistService extends CrudService<Wishlist, Long> {

    Wishlist findByUser(Integer userId, Long id);
    Wishlist findByWine(Integer userId, Long wineId);

}
