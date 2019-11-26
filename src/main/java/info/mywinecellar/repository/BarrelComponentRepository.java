/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.repository;

import info.mywinecellar.model.BarrelComponent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarrelComponentRepository extends JpaRepository<BarrelComponent, Long> {
}
