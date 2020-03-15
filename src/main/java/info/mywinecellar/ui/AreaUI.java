/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Grape;

import java.util.ArrayList;
import java.util.List;

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

    private List<Long> primaryGrapes;

    /**
     * Constructor
     */
    public AreaUI() {
        this.primaryGrapes = new ArrayList<>();
    }

    /**
     * Constructor
     * @param a The area
     */
    public AreaUI(Area a) {
        super(toKey(a.getName()));
        this.id = a.getId();
        this.name = a.getName();
        this.description = a.getDescription();
        this.weblink = a.getWeblink();

        this.primaryGrapes = new ArrayList<>();
        for (Grape g : a.getPrimaryGrapes()) {
            this.primaryGrapes.add(g.getId());
        }
    }
}
