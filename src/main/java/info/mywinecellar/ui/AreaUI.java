/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Area;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UI for area
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class AreaUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    /**
     * Constructor
     * @param a The area
     */
    AreaUI(Area a) {
        super(toKey(a.getName()));
        this.id = a.getId();
        this.name = a.getName();
        this.description = a.getDescription();
        this.weblink = a.getWeblink();
    }
}
