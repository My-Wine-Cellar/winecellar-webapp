/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ProducerUI factory
 */
public class ProducerUIFactory implements FactoryUI<Producer, ProducerUI> {

    private ProducerUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<Producer, ProducerUI> instance() {
        return new ProducerUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param producers The producers
     * @return The UI objects
     */
    public List<ProducerUI> createList(List<Producer> producers) {
        List<ProducerUI> result = new ArrayList<>();
        if (producers != null) {
            for (Producer p : producers) {
                result.add(create(p));
            }
            Collections.sort(result, new ProducerUISorter());
        }
        return result;
    }

    /**
     * Create a UI object
     * @param p A producer
     * @return The UI
     */
    public ProducerUI create(Producer p) {
        if (p == null) {
            return null;
        }

        return new ProducerUI(p);
    }
}
