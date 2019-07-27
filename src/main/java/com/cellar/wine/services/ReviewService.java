package com.cellar.wine.services;

import com.cellar.wine.models.Review;

public interface ReviewService extends CrudService<Review, Long> {

    Review findByUser(Integer userId, Long id);
    Review findByWine(Integer userId, Long wineId);

}
