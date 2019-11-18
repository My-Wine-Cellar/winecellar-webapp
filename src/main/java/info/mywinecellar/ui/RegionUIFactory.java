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

public class RegionUIFactory implements FactoryUI<Region, RegionUI> {

    private RegionUIFactory() {
    }

    public static FactoryUI<Region, RegionUI> instance() {
        return new RegionUIFactory();
    }

    public List<RegionUI> createList(List<Region> regions) {
        List<RegionUI> result = new ArrayList<>();
        if (regions != null) {
            for (Region r : regions) {
                result.add(create(r));
            }
        }
        return result;
    }

    public RegionUI create(Region r) {
        if (r == null) {
            return null;
        }

        return new RegionUI(r);
    }
}
