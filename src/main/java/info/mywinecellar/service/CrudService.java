/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

/**
 * Crud service
 * @param <T> The type
 * @param <ID> The identifier type
 */
public interface CrudService<T, ID> {

    /**
     * Find by id
     * @param id The identifier
     * @return The type
     */
    T findById(ID id);

    /**
     * Save
     * @param object The object
     * @return The updated object
     */
    T save(T object);

    /**
     * Delete
     * @param object The object
     */
    void delete(T object);

}
