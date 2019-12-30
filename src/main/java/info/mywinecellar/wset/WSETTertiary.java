/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wset_tertiary")
public class WSETTertiary extends BaseEntity implements Comparable<WSETTertiary> {

    /** DRIED_FRUIT (shared) */
    public static final Long DRIED_FRUIT = 1L;

    /** COOKED_FRUIT */
    public static final Long COOKED_FRUIT = 2L;

    /** LEATHER */
    public static final Long LEATHER = 3L;

    /** EARTH */
    public static final Long EARTH = 4L;

    /** MUSHROOM */
    public static final Long MUSHROOM = 5L;

    /** MEAT */
    public static final Long MEAT = 6L;

    /** TOBACCO */
    public static final Long TOBACCO = 7L;

    /** WET_LEAVES */
    public static final Long WET_LEAVES = 8L;

    /** FOREST_FLOOR */
    public static final Long FOREST_FLOOR = 9L;

    /** CARAMEL (shared) */
    public static final Long CARAMEL = 10L;

    /** ORANGE_MARMALADE */
    public static final Long ORANGE_MARMALADE = 11L;

    /** PETROL */
    public static final Long PETROL = 12L;

    /** CINNAMON */
    public static final Long CINNAMON = 13L;

    /** GINGER */
    public static final Long GINGER = 14L;

    /** NUTMEG */
    public static final Long NUTMEG = 15L;

    /** ALMOND */
    public static final Long ALMOND = 16L;

    /** HAZELNUT */
    public static final Long HAZELNUT = 17L;

    /** HONEY */
    public static final Long HONEY = 18L;

    /**
     * Default constructor
     */
    public WSETTertiary() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETTertiary(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseTertiary")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateTertiary")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETTertiary w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETTertiary)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETTertiary(" + id + ")";
    }
}
