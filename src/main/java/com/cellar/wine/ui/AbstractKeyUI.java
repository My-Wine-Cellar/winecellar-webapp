package com.cellar.wine.ui;

import com.cellar.wine.models.Producer;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public abstract class AbstractKeyUI implements Serializable {

    private String key;

    public AbstractKeyUI(String key) {
        this.key = key;
    }

    public static String fromKey(String s) {
        return s.toLowerCase().replace('_', ' ');
    }
    
    public static String toKey(String s) {
        return s.toLowerCase().replace(' ', '_');
    }
}
