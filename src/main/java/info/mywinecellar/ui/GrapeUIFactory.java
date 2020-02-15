/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Grape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GrapeUI factory
 */
public class GrapeUIFactory implements FactoryUI<Grape, GrapeUI> {

    private GrapeUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Grape, GrapeUI> instance() {
        return new GrapeUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param grapes The grapes
     * @return The UI objects
     */
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

    /**
     * Create a UI object
     * @param g A grape
     * @return The UI
     */
    public GrapeUI create(Grape g) {
        if (g == null) {
            return null;
        }

        return new GrapeUI(g);
    }
}
