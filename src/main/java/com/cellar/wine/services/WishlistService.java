package com.cellar.wine.services;

import com.cellar.wine.models.Wishlist;

public interface WishlistService extends CrudService<Wishlist, Long> {

    Wishlist findByUser(Long wineId, Integer userId);

}
