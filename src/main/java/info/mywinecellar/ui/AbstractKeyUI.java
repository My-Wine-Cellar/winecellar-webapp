/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Defines the key for a UI object
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
public abstract class AbstractKeyUI implements Serializable {

    private String key;

    /**
     * Default constructor
     */
    AbstractKeyUI() {
    }

    /**
     * Constructor
     * @param key The key
     */
    AbstractKeyUI(String key) {
        this.key = key;
    }

    /**
     * Set the key
     * @param k The key
     */
    void setKey(String k) {
        this.key = k;
    }

    /**
     * From key
     * @param s The key encoding
     * @return The string encoding
     */
    public static String fromKey(String s) {
        return s.toLowerCase().replace('_', ' ');
    }

    /**
     * To key
     * @param s The string encoding
     * @return The key encoding
     */
    public static String toKey(String s) {
        return s.toLowerCase().replace(' ', '_');
    }
}
