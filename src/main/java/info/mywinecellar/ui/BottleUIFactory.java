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

/**
 * BottleUI factory
 */
public class BottleUIFactory implements FactoryUI<Bottle, BottleUI> {

    private BottleUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Bottle, BottleUI> instance() {
        return new BottleUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param bottles The bottles
     * @return The UI objects
     */
    public List<BottleUI> createList(List<Bottle> bottles) {
        List<BottleUI> result = new ArrayList<>();
        if (bottles != null) {
            for (Bottle b : bottles) {
                result.add(create(b));
            }
        }
        return result;
    }

    /**
     * Create a UI object
     * @param b A bottle
     * @return The UI
     */
    public BottleUI create(Bottle b) {
        if (b == null) {
            return null;
        }

        return new BottleUI(b);
    }
}
