/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.TastedDto;
import info.mywinecellar.model.Review;
import info.mywinecellar.model.Tasted;
import info.mywinecellar.model.User;
import info.mywinecellar.service.ReviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class TastedConverter {

    @Inject
    ReviewService reviewService;

    /**
     * Convert entity to dto
     *
     * @param user   user
     * @param tasted tasted
     * @return dto object
     */
    public TastedDto toDto(User user, Tasted tasted) {
        Long reviewId = null;
        Review review = reviewService.findByWine(user.getId(), tasted.getWine().getId());
        if (review != null) {
            reviewId = review.getId();
        }
        return new TastedDto(tasted, reviewId);
    }

    /**
     * Convert entity list to dto list
     *
     * @param user       user
     * @param tastedList tastedList
     * @return dto list
     */
    public List<TastedDto> toDto(User user, Set<Tasted> tastedList) {
        List<TastedDto> result = new ArrayList<>();
        if (tastedList != null) {
            tastedList.forEach(tasted -> result.add(toDto(user, tasted)));
        }
        /* SORTING */
        return result;
    }
}
