/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Barrel;

import java.util.List;

/**
 * Barrel service
 */
public interface BarrelService extends CrudService<Barrel, Long> {

    /**
     * Find by lower case name
     * @param lcName The lower case name
     * @return The barrels
     */
    List<Barrel> findByLowerCaseName(String lcName);

    /**
     * Find all
     * @return The barrels
     */
    List<Barrel> findAll();
}
