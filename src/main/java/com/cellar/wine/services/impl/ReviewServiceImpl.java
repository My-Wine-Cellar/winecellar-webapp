/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

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
