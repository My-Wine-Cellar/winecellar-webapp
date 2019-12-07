/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Area;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AreaUI factory
 */
public class AreaUIFactory implements FactoryUI<Area, AreaUI> {

    private AreaUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Area, AreaUI> instance() {
        return new AreaUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param areas The areas
     * @return The UI objects
     */
    public List<AreaUI> createList(List<Area> areas) {
        List<AreaUI> result = new ArrayList<>();
        if (areas != null) {
            for (Area a : areas) {
                result.add(create(a));
            }
            Collections.sort(result, new AreaUISorter());
        }
        return result;
    }

    /**
     * Create a UI object
     * @param a An area
     * @return The UI
     */
    public AreaUI create(Area a) {
        if (a == null) {
            return null;
        }

        return new AreaUI(a);
    }
}
