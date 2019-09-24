package com.cellar.wine.services.impl;

import com.cellar.wine.models.Review;
import com.cellar.wine.repositories.ReviewRepository;
import com.cellar.wine.services.ReviewService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Inject
    private ReviewRepository reviewRepository;

    public Review findByUser(Integer userId, Long id) {
        return reviewRepository.findByUser(userId, id);
    }

    public Review findByWine(Integer userId, Long wineId) {
        return reviewRepository.findByWine(userId, wineId);
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

}
