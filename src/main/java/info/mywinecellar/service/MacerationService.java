/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.model.Maceration;

import org.springframework.stereotype.Component;

@Component
public class MacerationService extends AbstractService<Maceration> {

    /**
     * Constructor
     */
    public MacerationService() {
        super(Maceration.class);
    }

}
