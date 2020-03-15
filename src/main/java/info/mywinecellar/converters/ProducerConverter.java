/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converters;

import info.mywinecellar.model.Producer;
import info.mywinecellar.ui.ProducerUI;
import info.mywinecellar.ui.ProducerUISorter;
import info.mywinecellar.util.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Producer converter
 */
@Component
public class ProducerConverter {

    /**
     * Create a list of UI objects
     * @param producers The producers
     * @return The UI objects
     */
    public List<ProducerUI> toUIs(List<Producer> producers) {
        if (producers == null) {
            throw new IllegalStateException("Producers is null");
        }

        List<ProducerUI> result = new ArrayList<>();
        for (Producer p : producers) {
            result.add(toUI(p));
        }
        Collections.sort(result, new ProducerUISorter());

        return result;
    }

    /**
     * Create a UI object
     * @param p A producer
     * @return The UI
     */
    public ProducerUI toUI(Producer p) {
        if (p == null) {
            throw new IllegalStateException("Producer is null");
        }

        return new ProducerUI(p);
    }

    /**
     * Create a Producer entity
     * @param p The entity
     * @param ui The UI object
     * @return The new entity
     */
    public Producer toEntity(Producer p, ProducerUI ui) {
        if (p == null) {
            p = new Producer(ui.getName(), ui.getDescription(), ui.getPhone(),
                             ui.getFax(), ui.getEmail(), ui.getWebsite(),
                             Image.decode(ui.getImage()));
        } else {
            p.setDescription(ui.getDescription());
            p.setPhone(ui.getPhone());
            p.setFax(ui.getFax());
            p.setEmail(ui.getEmail());
            p.setWebsite(ui.getWebsite());
            p.setImage(Image.decode(ui.getImage()));
        }

        return p;
    }
}
