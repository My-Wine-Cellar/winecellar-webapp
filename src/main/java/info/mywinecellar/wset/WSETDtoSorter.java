/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.dto.WineDto;
import info.mywinecellar.dto.WineDtoSorter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sorter for WSETDto beans
 */
public class WSETDtoSorter implements Comparator<WSETDto> {

    private WineDtoSorter sorter;
    private Map<Long, WineDto> wines;

    /**
     * Constructor
     * @param l The wines
     */
    public WSETDtoSorter(List<WineDto> l) {
        sorter = new WineDtoSorter();
        wines = new HashMap<>();

        for (WineDto w : l) {
            wines.put(w.getId(), w);
        }
    }

    /**
     * Compare two WSET
     * @param w1 The first
     * @param w2 The second
     * @return 0 if equals, otherwise based on date
     */
    public int compare(WSETDto w1, WSETDto w2) {
        if (w1.getId().equals(w2.getId())) {
            return 0;
        }

        WineDto d1 = wines.get(w1.getWineId());
        WineDto d2 = wines.get(w2.getWineId());

        return sorter.compare(d1, d2);
    }
}

