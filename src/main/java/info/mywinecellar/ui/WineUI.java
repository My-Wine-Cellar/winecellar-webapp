/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Wine;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UI for wine
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class WineUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private Integer vintage;
    private Float size;

    /**
     * Constructor
     * @param w The wine
     */
    WineUI(Wine w) {
        super(toKey(w.getName()));
        this.id = w.getId();
        this.name = w.getName();
        this.vintage = w.getVintage();
        this.size = w.getSize();
    }
}
