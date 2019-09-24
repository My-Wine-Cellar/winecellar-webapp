package com.cellar.wine.services.impl;

import com.cellar.wine.models.Wishlist;
import com.cellar.wine.repositories.WishlistRepository;
import com.cellar.wine.services.WishlistService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Inject
    private WishlistRepository wishlistRepository;

    public Wishlist findByUser(Integer userId, Long id) {
        return wishlistRepository.findByUser(userId, id);
    }

    public Wishlist findByWine(Integer userId, Long wineId) {
        return wishlistRepository.findByWine(userId, wineId);
    }

    @Override
    public Wishlist findById(Long aLong) {
        return wishlistRepository.findById(aLong).orElse(null);
    }

    @Override
    public Wishlist save(Wishlist object) {
        return wishlistRepository.save(object);
    }

    @Override
    public void delete(Wishlist object) {
        wishlistRepository.delete(object);
    }

}
