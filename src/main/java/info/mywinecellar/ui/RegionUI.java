/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Region;

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
public class RegionUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    /**
     * Constructor
     * @param r The region
     */
    RegionUI(Region r) {
        super(toKey(r.getName()));
        this.id = r.getId();
        this.name = r.getName();
        this.description = r.getDescription();
        this.weblink = r.getWeblink();
    }
}
