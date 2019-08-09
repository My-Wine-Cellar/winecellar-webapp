package com.cellar.wine.ui;

import com.cellar.wine.models.GenericTastingNotes;

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
