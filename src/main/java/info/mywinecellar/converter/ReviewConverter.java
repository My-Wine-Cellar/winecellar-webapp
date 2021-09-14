/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.ReviewDto;
import info.mywinecellar.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for {@link Review} and {@link ReviewDto} conversion
 */
public final class ReviewConverter {

    private ReviewConverter() {
    }

    /**
     * Conver entity to dto
     *
     * @param review review
     * @return dto object
     */
    public static ReviewDto toDto(Review review) {
        if (review == null) {
            throw new IllegalStateException("Review is null");
        }
        return new ReviewDto(review);
    }

    /**
     * Convert entity list to dto list
     *
     * @param reviews reviews
     * @return dto list
     */
    public static List<ReviewDto> toDto(Set<Review> reviews) {
        if (reviews == null) {
            throw new IllegalStateException("Review list is null");
        }
        List<ReviewDto> result = new ArrayList<>();
        reviews.forEach(review -> result.add(toDto(review)));
        /* SORTING */
        return result;
    }
}
