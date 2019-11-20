/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * RegionUI factory
 */
public class RegionUIFactory implements FactoryUI<Region, RegionUI> {

    private RegionUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Region, RegionUI> instance() {
        return new RegionUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param regions The regions
     * @return The UI objects
     */
    public List<RegionUI> createList(List<Region> regions) {
        List<RegionUI> result = new ArrayList<>();
        if (regions != null) {
            for (Region r : regions) {
                result.add(create(r));
            }
        }
        return result;
    }

    /**
     * Create a UI object
     * @param r A region
     * @return The UI
     */
    public RegionUI create(Region r) {
        if (r == null) {
            return null;
        }

        return new RegionUI(r);
    }
}
