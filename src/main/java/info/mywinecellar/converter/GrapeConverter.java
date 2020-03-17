/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.model.Grape;
import info.mywinecellar.ui.GrapeUI;
import info.mywinecellar.ui.GrapeUISorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Grape converter
 */
@Component
public class GrapeConverter {

    /**
     * Create a list of UI objects
     * @param grapes The grapes
     * @return The UI objects
     */
    public List<GrapeUI> toUIs(List<Grape> grapes) {
        if (grapes == null) {
            throw new IllegalStateException("Grapes is null");
        }

        List<GrapeUI> result = new ArrayList<>();
        for (Grape g : grapes) {
            result.add(toUI(g));
        }
        Collections.sort(result, new GrapeUISorter());

        return result;
    }

    /**
     * Create a UI object
     * @param g A grape
     * @return The UI
     */
    public GrapeUI toUI(Grape g) {
        if (g == null) {
            throw new IllegalStateException("Grape is null");
        }

        return new GrapeUI(g);
    }

    /**
     * Create a Grape entity
     * @param g The entity
     * @param ui The UI object
     * @return The new entity
     */
    public Grape toEntity(Grape g, GrapeUI ui) {
        if (g == null) {
            g = new Grape(ui.getName(), ui.getColor(), ui.getDescription(), ui.getWeblink());
        } else {
            g.setDescription(ui.getDescription());
            g.setWeblink(ui.getWeblink());
        }

        return g;
    }
}
