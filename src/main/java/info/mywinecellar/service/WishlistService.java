/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Wishlist;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class WishlistService extends AbstractService<Wishlist> {

    /**
     * Constructor
     */
    public WishlistService() {
        super(Wishlist.class);
    }

    /**
     * Find Wishlist for a User
     *
     * @param userId Integer userId
     * @param id     Long id
     * @return Wishlist entity
     */
    public Wishlist findByUser(Integer userId, Long id) {
        try {
            Query query = em.createQuery("SELECT w FROM Wishlist w WHERE w.user.id = :userid AND w.id = :id");
            query.setParameter("userid", userId);
            query.setParameter("id", id);
            return (Wishlist) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Wishlist for a Wine
     *
     * @param userId Integer userId
     * @param wineId Long wineId
     * @return Wishlist entity
     */
    public Wishlist findByWine(Integer userId, Long wineId) {
        try {
            Query query = em.createQuery("SELECT w FROM Wishlist w WHERE w.user.id = :userid AND w.wine.id = :wineid");
            query.setParameter("userid", userId);
            query.setParameter("wineid", wineId);
            return (Wishlist) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
