package com.cellar.wine.services.impl;

import com.cellar.wine.models.Review;
import com.cellar.wine.repositories.ReviewRepository;
import com.cellar.wine.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review findByUser(Long wineId, Integer userId) {
        return reviewRepository.findByUser(wineId, userId);
    }

    @Override
    public Set<Review> findAll() {
        Set<Review> reviews = new TreeSet<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    @Override
    public Review findById(Long aLong) {
        return reviewRepository.findById(aLong).orElse(null);
    }

    @Override
    public Review save(Review object) {
        return reviewRepository.save(object);
    }

    @Override
    public void delete(Review object) {
        reviewRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        reviewRepository.deleteById(aLong);
    }

}
