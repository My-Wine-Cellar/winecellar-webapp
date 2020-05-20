/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Sorter for dto beans
 */
public class AreaDtoSorter implements Comparator<AreaDto> {

    private Map<Long, String> regions;

    /**
     * Constructor
     *
     * @param dtos The regions
     */
    public AreaDtoSorter(Collection<RegionDto> dtos) {
        regions = new HashMap<>();
        dtos.forEach(regionDto -> regions.put(regionDto.getId(), regionDto.getName()));
    }

    /**
     * Compare two countries
     *
     * @param a1 The first area
     * @param a2 The second area
     * @return 0 if equals, otherwise based on name
     */
    public int compare(AreaDto a1, AreaDto a2) {

        if (a1.getName().equals(regions.get(a1.getRegionId()))) {
            return -1;
        }

        if (a2.getName().equals(regions.get(a2.getRegionId()))) {
            return 1;
        }

        return a1.getName().compareTo(a2.getName());
    }
}

