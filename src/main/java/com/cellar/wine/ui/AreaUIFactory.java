package com.cellar.wine.ui;

import com.cellar.wine.models.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaUIFactory implements FactoryUI<Area, AreaUI> {

    private AreaUIFactory() {
    }

    public static FactoryUI<Area, AreaUI> instance() {
        return new AreaUIFactory();
    }

    public List<AreaUI> createList(List<Area> areas) {
        List<AreaUI> result = new ArrayList<>();
        if (areas != null) {
            for (Area a : areas) {
                result.add(create(a));
            }
        }
        return result;
    }

    public AreaUI create(Area a) {
        if (a == null) {
            return null;
        }

        return new AreaUI(a);
    }
}
