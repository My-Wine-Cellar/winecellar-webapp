/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.model.Wine;
import info.mywinecellar.ui.WineUI;
import info.mywinecellar.ui.WineUISorter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WineConverter {

    /**
     * Convert Wine entity to WineUI
     *
     * @param wine wine
     * @return WineUI
     */
    public WineUI toUI(Wine wine) {
        if (wine == null) {
            throw new IllegalStateException("Wine is null");
        }
        return new WineUI(wine);
    }

    /**
     * Convert Wine list to WineUI list
     *
     * @param wines the list of wines
     * @return WineUI list
     */
    public List<WineUI> toUIs(List<Wine> wines) {
        if (wines == null) {
            throw new IllegalStateException("Wine list is null");
        }
        List<WineUI> result = new ArrayList<>();
        wines.forEach(wine -> result.add(toUI(wine)));
        result.sort(new WineUISorter());
        return result;
    }

    /**
     * Convert WineUI to Wine
     *
     * @param entity Wine entity
     * @param ui     WineUI ui
     * @return Wine entity
     */
    public Wine toEntity(Wine entity, WineUI ui) {
        if (entity == null) {
            entity = new Wine(ui.getName(), ui.getVintage(), ui.getSize());
        } else {
            entity.setName(ui.getName());
            entity.setVintage(ui.getVintage());
            entity.setSize(ui.getSize());
        }
        return entity;
    }
}
