/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.model.Region;
import info.mywinecellar.ui.RegionUI;
import info.mywinecellar.ui.RegionUISorter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RegionConverter {

    /**
     * Convert Region entity to RegionUI
     *
     * @param region Region region
     * @return RegionUI object
     */
    public RegionUI toUI(Region region) {
        if (region == null) {
            throw new IllegalStateException("Region is null");
        }
        return new RegionUI(region);
    }

    /**
     * Convert a list of Regions to a list of RegionUI objects
     *
     * @param regions List<Region> regions
     * @return List<RegionUI> regionUI's
     */
    public List<RegionUI> toUIs(List<Region> regions) {
        if (regions == null) {
            throw new IllegalStateException("Region list is null");
        }
        List<RegionUI> result = new ArrayList<>();
        regions.forEach(region -> result.add(toUI(region)));
        result.sort(new RegionUISorter());
        return result;
    }

    /**
     * Update and convert a Region entity from RegionUI to prepare for save
     *
     * @param entity Region entity
     * @param ui     RegionUI ui
     * @return Region entity
     */
    public Region toEntity(Region entity, RegionUI ui) {
        if (entity == null) {
            entity = new Region(ui.getName(), ui.getDescription(), ui.getWeblink());
        } else {
            entity.setDescription(ui.getDescription());
            entity.setWeblink(ui.getWeblink());
        }
        return entity;
    }
}
