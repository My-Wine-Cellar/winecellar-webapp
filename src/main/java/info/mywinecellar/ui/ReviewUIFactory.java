/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * ReviewUI factory
 */
public class ReviewUIFactory implements FactoryUI<Review, ReviewUI> {

    private ReviewUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Review, ReviewUI> instance() {
        return new ReviewUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param reviews The reviews
     * @return The UI objects
     */
    public List<ReviewUI> createList(List<Review> reviews) {
        List<ReviewUI> result = new ArrayList<>();
        if (reviews != null) {
            for (Review r : reviews) {
                result.add(create(r));
            }
        }
        return result;
    }

    /**
     * Create a UI object
     * @param r A review
     * @return The UI
     */
    public ReviewUI create(Review r) {
        if (r == null) {
            return null;
        }

        return new ReviewUI(r);
    }
}
