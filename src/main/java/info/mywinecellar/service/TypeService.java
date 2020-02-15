/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Type;

import java.util.List;

public interface TypeService extends CrudService<Type, Long> {

    /**
     * Find all
     * @return All types
     */
    List<Type> findAll();
}
