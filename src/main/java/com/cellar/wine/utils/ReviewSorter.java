package com.cellar.wine.utils;

import com.cellar.wine.models.Review;

import java.util.Comparator;

/**
 * Sorter for Review entities
 */
public class ReviewSorter implements Comparator<Review> {
    private WineSorter wineSorter;

    public ReviewSorter() {
        this(false);
    }

    public ReviewSorter(boolean country) {
        wineSorter = new WineSorter(country);
    }

    public int compare(Review r1, Review r2) {
        int result;
        
        if (r1.getId().equals(r2.getId()))
            return 0;

        result = r1.getStars().compareTo(r2.getStars());
        if (result != 0)
            return -result;

        return wineSorter.compare(r1.getWine(), r2.getWine());
    }
}

