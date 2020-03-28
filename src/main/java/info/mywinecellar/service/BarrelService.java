/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Barrel;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class BarrelService extends AbstractService<Barrel> {

    /**
     * Constructor
     */
    public BarrelService() {
        super(Barrel.class);
    }

    /**
     * Find barrel by it's lowercase name
     * @param lcName String lcName
     * @return The list of barrels
     */
    public List<Barrel> findByLowerCaseName(String lcName) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM barrel b WHERE lower(b.name) " +
                            "LIKE :lc_name ORDER BY b.id",
                    Barrel.class);
            query.setParameter("lc_name", lcName);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
