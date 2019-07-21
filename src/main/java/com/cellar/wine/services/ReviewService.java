package com.cellar.wine.services;

import com.cellar.wine.models.Review;

public interface ReviewService extends CrudService<Review, Long> {

    Review findByUser(Long wineId, Integer userId);

}
