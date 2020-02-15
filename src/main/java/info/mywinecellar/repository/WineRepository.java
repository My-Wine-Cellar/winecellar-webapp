/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.Wine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Long> {

    /**
     * @param name name
     * @return Wine
     */
    Wine findByName(String name);

    /**
     * @param name name
     * @return Wine list
     */
    List<Wine> findAllByName(String name);

}
