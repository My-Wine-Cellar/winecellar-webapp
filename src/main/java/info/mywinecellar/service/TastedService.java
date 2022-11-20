/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Tasted;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class TastedService extends AbstractService<Tasted> {

    /**
     * Constructor
     */
    public TastedService() {
        super(Tasted.class);
    }

    /**
     * Find Tasted for a User
     *
     * @param userId Integer userId
     * @param id     Long id
     * @return Tasted entity
     */
    public Tasted findByUser(Integer userId, Long id) {
        try {
            Query query = em.createQuery("SELECT t FROM Tasted t WHERE t.user.id = :userid AND t.id = :id");
            query.setParameter("userid", userId);
            query.setParameter("id", id);
            return (Tasted) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find Tasted for a Wine
     *
     * @param userId Integer userId
     * @param wineId Long wineId
     * @return Tasted entity
     */
    public Tasted findByWine(Integer userId, Long wineId) {
        try {
            Query query = em.createQuery("SELECT t FROM Tasted t WHERE t.user.id = :userid AND t.wine.id = :wineid");
            query.setParameter("userid", userId);
            query.setParameter("wineid", wineId);
            return (Tasted) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
