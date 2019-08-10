package com.cellar.wine.ui;

import com.cellar.wine.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishlistUIFactory implements FactoryUI<Wishlist, WishlistUI> {

    private WishlistUIFactory() {
    }

    public static FactoryUI<Wishlist, WishlistUI> instance() {
        return new WishlistUIFactory();
    }

    public List<WishlistUI> createList(List<Wishlist> wishlist) {
        List<WishlistUI> result = new ArrayList<>();
        if (wishlist != null) {
            for (Wishlist w : wishlist) {
                result.add(create(w));
            }
        }
        return result;
    }

    public WishlistUI create(Wishlist w) {
        if (w == null) {
            return null;
        }

        return new WishlistUI(w);
    }
}
