/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wset_appearance_color")
public class WSETAppearanceColor extends BaseEntity implements Comparable<WSETAppearanceColor> {

    /** LEMON */
    public static final Long LEMON = 1L;

    /** GOLD */
    public static final Long GOLD = 2L;

    /** AMBER */
    public static final Long AMBER = 3L;

    /** BROWN */
    public static final Long BROWN = 4L;

    /** PINK */
    public static final Long PINK = 5L;

    /** PINK/ORANGE */
    public static final Long PINK_ORANGE = 6L;

    /** ORANGE */
    public static final Long ORANGE = 7L;

    /** PURPLE */
    public static final Long PURPLE = 8L;

    /** RUBY */
    public static final Long RUBY = 9L;

    /** GARNET */
    public static final Long GARNET = 10L;

    /** TAWNY */
    public static final Long TAWNY = 11L;

    /**
     * Default constructor
     */
    public WSETAppearanceColor() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETAppearanceColor(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETAppearanceColor w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETAppearanceColor)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETAppearanceColor(" + id + ")";
    }
}
