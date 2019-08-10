package com.cellar.wine.ui;

import com.cellar.wine.models.Grape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrapeUIFactory implements FactoryUI<Grape, GrapeUI> {

    private GrapeUIFactory() {
    }

    public static FactoryUI<Grape, GrapeUI> instance() {
        return new GrapeUIFactory();
    }

    public List<GrapeUI> createList(List<Grape> grapes) {
        List<GrapeUI> result = new ArrayList<>();
        if (grapes != null) {
            for (Grape g : grapes) {
                result.add(create(g));
            }
            Collections.sort(result, new GrapeUISorter());
        }
        return result;
    }

    public GrapeUI create(Grape g) {
        if (g == null) {
            return null;
        }

        return new GrapeUI(g);
    }
}
