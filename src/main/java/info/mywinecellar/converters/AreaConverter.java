/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converters;

import info.mywinecellar.model.Area;
import info.mywinecellar.ui.AreaUI;
import info.mywinecellar.ui.AreaUISorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Area converter
 */
@Component
public class AreaConverter {

    /**
     * Create a list of UI objects
     * @param areas The areas
     * @return The UI objects
     */
    public List<AreaUI> toUIs(List<Area> areas) {
        if (areas == null) {
            throw new IllegalStateException("Areas is null");
        }

        List<AreaUI> result = new ArrayList<>();
        for (Area a : areas) {
            result.add(toUI(a));
        }
        Collections.sort(result, new AreaUISorter());

        return result;
    }

    /**
     * Create a UI object
     * @param a An area
     * @return The UI
     */
    public AreaUI toUI(Area a) {
        if (a == null) {
            throw new IllegalStateException("Area is null");
        }

        return new AreaUI(a);
    }

    /**
     * Create an Area entity
     * @param a An area
     * @param ui The UI object
     * @return The area
     */
    public Area toEntity(Area a, AreaUI ui) {
        if (a == null) {
            a = new Area(ui.getName(), ui.getDescription(), ui.getWeblink());
        } else {
            a.setDescription(ui.getDescription());
            a.setWeblink(ui.getWeblink());
        }

        return a;
    }
}
