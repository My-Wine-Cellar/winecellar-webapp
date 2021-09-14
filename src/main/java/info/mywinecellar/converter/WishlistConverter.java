/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.WishlistDto;
import info.mywinecellar.model.Wishlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link Wishlist} and {@link WishlistDto} conversion
 */
public final class WishlistConverter {

    private WishlistConverter() {
    }

    /**
     * Convert entity to dto
     *
     * @param wishlist wishlist
     * @return dto object
     */
    private static WishlistDto toDto(Wishlist wishlist) {
        if (wishlist == null) {
            throw new IllegalStateException("Wishlist is null");
        }
        return new WishlistDto(wishlist);
    }

    /**
     * Convert entity list to dto list
     *
     * @param wishlists wishlists
     * @return dto list
     */
    public static List<WishlistDto> toDto(Set<Wishlist> wishlists) {
        if (wishlists == null) {
            throw new IllegalStateException("Wishlist list is null");
        }
        List<WishlistDto> result = new ArrayList<>();
        wishlists.forEach(wishlist -> result.add(toDto(wishlist)));
        /* SORTING */
        return result;
    }
}
