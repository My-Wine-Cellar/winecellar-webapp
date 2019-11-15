/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.ui;

import com.cellar.wine.models.Wine;

import java.util.ArrayList;
import java.util.List;

public class WineUIFactory implements FactoryUI<Wine, WineUI> {

    private WineUIFactory() {
    }

    public static FactoryUI<Wine, WineUI> instance() {
        return new WineUIFactory();
    }

    public List<WineUI> createList(List<Wine> wines) {
        List<WineUI> result = new ArrayList<>();
        if (wines != null) {
            for (Wine w : wines) {
                result.add(create(w));
            }
        }
        return result;
    }

    public WineUI create(Wine w) {
        if (w == null) {
            return null;
        }

        return new WineUI(w);
    }
}
