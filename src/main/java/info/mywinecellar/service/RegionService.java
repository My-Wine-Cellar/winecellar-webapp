/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Region;

/**
 * Region service
 */
public interface RegionService extends CrudService<Region, Long> {

    /**
     * Find by lower case name
     * @param lcName The lower case name
     * @param countryId The country identifier
     * @return The region
     */
    Region findByLowerCaseName(String lcName, Long countryId);
}
