/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.BarrelComponent;

public interface BarrelComponentService extends CrudService<BarrelComponent, Long> {

    Iterable<BarrelComponent> saveAll(Iterable<BarrelComponent> barrels);
}
