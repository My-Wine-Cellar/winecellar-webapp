package com.cellar.wine.ui;

import com.cellar.wine.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewUIFactory implements FactoryUI<Review, ReviewUI> {

    private ReviewUIFactory() {
    }

    public static FactoryUI<Review, ReviewUI> instance() {
        return new ReviewUIFactory();
    }

    public List<ReviewUI> createList(List<Review> reviews) {
        List<ReviewUI> result = new ArrayList<>();
        if (reviews != null) {
            for (Review r : reviews) {
                result.add(create(r));
            }
        }
        return result;
    }

    public ReviewUI create(Review r) {
        if (r == null) {
            return null;
        }

        return new ReviewUI(r);
    }
}
