package com.cellar.wine.ui;

import com.cellar.wine.models.Bottle;

import java.util.ArrayList;
import java.util.List;

public class BottleUIFactory implements FactoryUI<Bottle, BottleUI> {

    private BottleUIFactory() {
    }

    public static FactoryUI<Bottle, BottleUI> instance() {
        return new BottleUIFactory();
    }

    public List<BottleUI> createList(List<Bottle> bottles) {
        List<BottleUI> result = new ArrayList<>();
        if (bottles != null) {
            for (Bottle b : bottles) {
                result.add(create(b));
            }
        }
        return result;
    }

    public BottleUI create(Bottle b) {
        if (b == null) {
            return null;
        }

        return new BottleUI(b);
    }
}
