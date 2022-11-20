/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Bottle;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class BottleService extends AbstractService<Bottle> {

    /**
     * Constructor
     */
    public BottleService() {
        super(Bottle.class);
    }

    /**
     * Find Bottle for a User
     *
     * @param userId Integer userId
     * @param id     Long id
     * @return Bottle entity
     */
    public Bottle findByUser(Integer userId, Long id) {
        try {
            Query query = em.createQuery("SELECT b FROM Bottle b WHERE b.user.id = :userid AND b.id = :id");
            query.setParameter("userid", userId);
            query.setParameter("id", id);
            return (Bottle) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Bottle for a Wine
     *
     * @param userId Integer userId
     * @param wineId Long wineId
     * @return Bottle entity
     */
    public Bottle findByWine(Integer userId, Long wineId) {
        try {
            Query query = em.createQuery("SELECT b FROM Bottle b WHERE b.user.id = :userid AND b.wine.id = :wineid");
            query.setParameter("userid", userId);
            query.setParameter("wineid", wineId);
            return (Bottle) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
