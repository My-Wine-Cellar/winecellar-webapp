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

/**
 * WishlistUI factory
 */
public class WishlistUIFactory implements FactoryUI<Wishlist, WishlistUI> {

    private WishlistUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Wishlist, WishlistUI> instance() {
        return new WishlistUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param wishlist The wishlist
     * @return The UI objects
     */
    public List<WishlistUI> createList(List<Wishlist> wishlist) {
        List<WishlistUI> result = new ArrayList<>();
        if (wishlist != null) {
            for (Wishlist w : wishlist) {
                result.add(create(w));
            }
        }
        return result;
    }

    /**
     * Create a UI object
     * @param w A wishlist
     * @return The UI
     */
    public WishlistUI create(Wishlist w) {
        if (w == null) {
            return null;
        }

        return new WishlistUI(w);
    }
}
