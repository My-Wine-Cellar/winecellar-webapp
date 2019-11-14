/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Country;

import java.util.List;

/**
 * Country service
 */
public interface CountryService extends CrudService<Country, Long> {

    /**
     * Find by lowercase name
     *
     * @param lcName The lower case name
     * @return The country
     */
    Country findByLowerCaseName(String lcName);

    /**
     * Find countries with wine producing regions
     *
     * @return The countries
     */
    List<Country> findWithRegions();

}
