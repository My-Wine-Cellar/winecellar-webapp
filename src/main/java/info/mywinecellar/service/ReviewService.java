/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Review;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class ReviewService extends AbstractService<Review> {

    /**
     * Constructor
     */
    public ReviewService() {
        super(Review.class);
    }

    /**
     * Find Review for a User
     *
     * @param userId Integer userId
     * @param id     Long id
     * @return Review entity
     */
    public Review findByUser(Integer userId, Long id) {
        try {
            Query query = em.createQuery("SELECT r FROM Review r WHERE r.user.id = :userid AND r.id = :id");
            query.setParameter("userid", userId);
            query.setParameter("id", id);
            return (Review) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Review for a Wine
     *
     * @param userId Integer userId
     * @param wineId Long wineId
     * @return Review entity
     */
    public Review findByWine(Integer userId, Long wineId) {
        try {
            Query query = em.createQuery("SELECT r FROM Review r WHERE r.user.id = :userid AND r.wine.id = :wineid");
            query.setParameter("userid", userId);
            query.setParameter("wineid", wineId);
            return (Review) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
