/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

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

    AbstractKeyUI() {
    }

    AbstractKeyUI(String key) {
        this.key = key;
    }

    void setKey(String k) {
        this.key = k;
    }

    public static String fromKey(String s) {
        return s.toLowerCase().replace('_', ' ');
    }
    
    public static String toKey(String s) {
        return s.toLowerCase().replace(' ', '_');
    }
}
