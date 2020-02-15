/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service.impl;

import info.mywinecellar.model.Review;
import info.mywinecellar.repository.ReviewRepository;
import info.mywinecellar.service.ReviewService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Review service implementation
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Inject
    private ReviewRepository reviewRepository;

    @Override
    public Review findByUser(Integer userId, Long id) {
        return reviewRepository.findByUser(userId, id);
    }

    @Override
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
