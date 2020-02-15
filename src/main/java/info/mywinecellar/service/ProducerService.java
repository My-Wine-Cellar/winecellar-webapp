/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Producer;

/**
 * Producer service
 */
public interface ProducerService extends CrudService<Producer, Long> {

    /**
     * Find by name
     *
     * @param name The name
     * @return The producer
     */
    Producer findByName(String name);

    /**
     * Update Producer
     *
     * @param model producer
     * @param save  producer
     */
    void update(Producer model, Producer save);

}
