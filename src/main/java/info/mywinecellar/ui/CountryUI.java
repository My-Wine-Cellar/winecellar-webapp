/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Country;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UI for wishlist
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class CountryUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String flag;
    private String description;
    private String weblink;

    /**
     * Constructor
     * @param c The country
     */
    CountryUI(Country c) {
        super(toKey(c.getName()));
        this.id = c.getId();
        this.name = c.getName();
        this.flag = c.getFlag();
        this.description = c.getDescription();
        this.weblink = c.getWeblink();
    }
}
