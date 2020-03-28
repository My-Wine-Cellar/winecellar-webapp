/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.GenericTastingNotes;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class TastingNotesService extends AbstractService<GenericTastingNotes> {

    /**
     * Constructor
     */
    public TastingNotesService() {
        super(GenericTastingNotes.class);
    }

    /**
     * Find TastingNotes for a User
     *
     * @param userId Integer userId
     * @param id     Long id
     * @return TastingNotes entity
     */
    public GenericTastingNotes findByUser(Integer userId, Long id) {
        try {
            Query query = em.createQuery("SELECT gtn from GenericTastingNotes gtn " +
                    "WHERE gtn.user.id = :userid AND gtn.id = :id");
            query.setParameter("userid", userId);
            query.setParameter("id", id);
            return (GenericTastingNotes) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find TastingNotes for a Wine
     *
     * @param userId Integer userId
     * @param wineId Long wineId
     * @return TastingNotes entity
     */
    public GenericTastingNotes findByWine(Integer userId, Long wineId) {
        try {
            Query query = em.createQuery("SELECT gtn FROM GenericTastingNotes gtn " +
                    "WHERE gtn.user.id = :userid AND gtn.wine.id = :wineid");
            query.setParameter("userid", userId);
            query.setParameter("wineid", wineId);
            return (GenericTastingNotes) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
