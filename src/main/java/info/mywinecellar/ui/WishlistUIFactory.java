/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Wishlist;

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
