/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Barrel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BarrelUI factory
 */
public class BarrelUIFactory implements FactoryUI<Barrel, BarrelUI> {

    private BarrelUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Barrel, BarrelUI> instance() {
        return new BarrelUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param barrels The barrels
     * @return The UI objects
     */
    public List<BarrelUI> createList(List<Barrel> barrels) {
        List<BarrelUI> result = new ArrayList<>();
        if (barrels != null) {
            for (Barrel b : barrels) {
                result.add(create(b));
            }
            Collections.sort(result, new BarrelUISorter());
        }
        return result;
    }

    /**
     * Create a UI object
     * @param b A barrel
     * @return The UI
     */
    public BarrelUI create(Barrel b) {
        if (b == null) {
            return null;
        }

        return new BarrelUI(b);
    }
}
