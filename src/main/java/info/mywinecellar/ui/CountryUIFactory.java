/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CountryUI factory
 */
public class CountryUIFactory implements FactoryUI<Country, CountryUI> {

    private CountryUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Country, CountryUI> instance() {
        return new CountryUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param countries The countries
     * @return The UI objects
     */
    public List<CountryUI> createList(List<Country> countries) {
        List<CountryUI> result = new ArrayList<>();
        if (countries != null) {
            for (Country c : countries) {
                result.add(create(c));
            }
            Collections.sort(result, new CountryUISorter());
        }
        return result;
    }

    /**
     * Create a UI object
     * @param c A country
     * @return The UI
     */
    public CountryUI create(Country c) {
        if (c == null) {
            return null;
        }

        return new CountryUI(c);
    }
}
