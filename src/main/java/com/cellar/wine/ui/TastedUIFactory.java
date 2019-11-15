/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.ui;

import com.cellar.wine.models.Review;
import com.cellar.wine.models.Tasted;
import com.cellar.wine.security.model.User;
import com.cellar.wine.services.ReviewService;

import java.util.ArrayList;
import java.util.List;

public class TastedUIFactory {

    private TastedUIFactory() {
    }

    public static TastedUIFactory instance() {
        return new TastedUIFactory();
    }

    public List<TastedUI> createList(ReviewService reviewService, User user, List<Tasted> tasted) {
        List<TastedUI> result = new ArrayList<>();
        if (tasted != null) {
            for (Tasted t : tasted) {
                result.add(create(reviewService, user, t));
            }
        }
        return result;
    }

    public TastedUI create(ReviewService reviewService, User user, Tasted t) {
        Long reviewId = null;
        Review review = reviewService.findByWine(user.getId(), t.getWine().getId());

        if (review != null)
            reviewId = review.getId();

        return new TastedUI(t, reviewId);
    }
}
