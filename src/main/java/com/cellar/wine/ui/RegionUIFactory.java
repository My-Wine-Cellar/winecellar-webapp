package com.cellar.wine.ui;

import com.cellar.wine.models.Region;

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
