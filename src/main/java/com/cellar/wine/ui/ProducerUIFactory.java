package com.cellar.wine.ui;

import com.cellar.wine.models.Producer;

import java.util.ArrayList;
import java.util.List;

public class ProducerUIFactory implements FactoryUI<Producer, ProducerUI> {

    private ProducerUIFactory() {
    }

    public static FactoryUI<Producer, ProducerUI> instance() {
        return new ProducerUIFactory();
    }

    public List<ProducerUI> createList(List<Producer> producers) {
        List<ProducerUI> result = new ArrayList<>();
        if (producers != null) {
            for (Producer p : producers) {
                result.add(create(p));
            }
        }
        return result;
    }

    public ProducerUI create(Producer p) {
        if (p == null) {
            return null;
        }

        return new ProducerUI(p);
    }
}
