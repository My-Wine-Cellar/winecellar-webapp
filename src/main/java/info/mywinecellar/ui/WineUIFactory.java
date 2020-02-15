/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Wine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * WineUI factory
 */
public class WineUIFactory implements FactoryUI<Wine, WineUI> {

    private WineUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Wine, WineUI> instance() {
        return new WineUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param wines The wines
     * @return The UI objects
     */
    public List<WineUI> createList(List<Wine> wines) {
        List<WineUI> result = new ArrayList<>();
        if (wines != null) {
            for (Wine w : wines) {
                result.add(create(w));
            }
            Collections.sort(result, new WineUISorter());
        }
        return result;
    }

    /**
     * Create a UI object
     * @param w A wine
     * @return The UI
     */
    public WineUI create(Wine w) {
        if (w == null) {
            return null;
        }

        return new WineUI(w);
    }
}
