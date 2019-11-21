/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Area;

/**
 * Area service
 */
public interface AreaService extends CrudService<Area, Long> {

    /**
     * Find by name
     * @param name The name
     * @return The area
     */
    Area findByName(String name);
}
