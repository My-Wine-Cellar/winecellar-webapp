/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.ui;

import com.cellar.wine.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryUIFactory implements FactoryUI<Country, CountryUI> {

    private CountryUIFactory() {
    }

    public static FactoryUI<Country, CountryUI> instance() {
        return new CountryUIFactory();
    }

    public List<CountryUI> createList(List<Country> countries) {
        List<CountryUI> result = new ArrayList<>();
        if (countries != null) {
            for (Country c : countries) {
                result.add(create(c));
            }
        }
        return result;
    }

    public CountryUI create(Country c) {
        if (c == null) {
            return null;
        }

        return new CountryUI(c);
    }
}
