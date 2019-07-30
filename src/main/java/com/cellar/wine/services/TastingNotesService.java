package com.cellar.wine.services;

import com.cellar.wine.models.GenericTastingNotes;

public interface TastingNotesService extends CrudService<GenericTastingNotes, Long> {

    GenericTastingNotes findByUser(Integer userId, Long id);

    GenericTastingNotes findByWine(Integer userId, Long wineId);
}
