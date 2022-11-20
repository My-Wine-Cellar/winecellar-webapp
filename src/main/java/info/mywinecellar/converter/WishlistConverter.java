/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.WishlistDto;
import info.mywinecellar.model.Wishlist;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return Optional.ofNullable(wishlist)
                .map(WishlistDto::new)
                .orElse(null);
    }

    /**
     * Convert entity list to dto list
     *
     * @param wishlists wishlists
     * @return dto list
     */
    public static List<WishlistDto> toDto(Set<Wishlist> wishlists) {
        return wishlists.stream()
                .map(WishlistConverter::toDto)
                .sorted(Comparator.comparing(WishlistDto::getDate))
                .collect(Collectors.toList());
    }
}
