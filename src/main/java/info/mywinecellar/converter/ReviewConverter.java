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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return Optional.ofNullable(review)
                .map(ReviewDto::new)
                .orElse(null);
    }

    /**
     * Convert entity list to dto list
     *
     * @param reviews reviews
     * @return dto list
     */
    public static List<ReviewDto> toDto(Set<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toDto)
                .sorted(Comparator.comparing(ReviewDto::getStars))
                .collect(Collectors.toList());
    }
}
