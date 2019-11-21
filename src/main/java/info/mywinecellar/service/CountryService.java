/*
 * My-Wine-Cellar, copyright 2019
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
     * Find by lower case name
     * @param lcName The lower case name
     * @return The country
     */
    Country findByLowerCaseName(String lcName);

    /**
     * Find countries with regions
     * @return The countries
     */
    List<Country> findWithRegions();

}
