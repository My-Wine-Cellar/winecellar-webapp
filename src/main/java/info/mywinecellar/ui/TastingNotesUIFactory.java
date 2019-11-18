/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.GenericTastingNotes;

import java.util.ArrayList;
import java.util.List;

public class TastingNotesUIFactory implements FactoryUI<GenericTastingNotes, TastingNotesUI> {

    private TastingNotesUIFactory() {
    }

    public static FactoryUI<GenericTastingNotes, TastingNotesUI> instance() {
        return new TastingNotesUIFactory();
    }

    public List<TastingNotesUI> createList(List<GenericTastingNotes> gtns) {
        List<TastingNotesUI> result = new ArrayList<>();
        if (gtns != null) {
            for (GenericTastingNotes g : gtns) {
                result.add(create(g));
            }
        }
        return result;
    }

    public TastingNotesUI create(GenericTastingNotes g) {
        if (g == null) {
            return null;
        }

        return new TastingNotesUI(g);
    }
}
