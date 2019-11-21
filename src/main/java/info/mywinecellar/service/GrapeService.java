/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Grape;

import java.util.List;

/**
 * Grape service
 */
public interface GrapeService extends CrudService<Grape, Long> {

    /**
     * Find by name
     * @param name The name
     * @return The grape
     */
    Grape findByName(String name);

    /**
     * Find all white grapes
     * @return The grapes
     */
    List<Grape> getWhiteGrapes();

    /**
     * Find all red grapes
     * @return The grapes
     */
    List<Grape> getRedGrapes();

    /**
     * Find all grapes
     * @return The grapes
     */
    List<Grape> findAll();

    /**
     * Find by lower case name
     * @param lcName The lower case name
     * @return The grape
     */
    Grape findByLowerCaseName(String lcName);
}
