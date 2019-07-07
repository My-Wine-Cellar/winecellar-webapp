package com.cellar.wine.services.impl;

import com.cellar.wine.models.Wishlist;
import com.cellar.wine.repositories.WishlistRepository;
import com.cellar.wine.services.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class WishlistServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Set<Wishlist> findAll() {
        Set<Wishlist> wishlists = new TreeSet<>();
        wishlistRepository.findAll().forEach(wishlists::add);
        return wishlists;
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

    @Override
    public void deleteById(Long aLong) {
        wishlistRepository.deleteById(aLong);
    }

}
