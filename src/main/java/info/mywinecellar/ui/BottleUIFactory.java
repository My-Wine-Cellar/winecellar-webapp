/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Bottle;

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
