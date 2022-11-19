/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wset_tropical_fruit")
public class WSETTropicalFruit extends BaseEntity implements Comparable<WSETTropicalFruit> {

    /** BANANA */
    public static final Long BANANA = 1L;

    /** LYCHEE */
    public static final Long LYCHEE = 2L;

    /** MANGO */
    public static final Long MANGO = 3L;

    /** MELON */
    public static final Long MELON = 4L;

    /** PASSION_FRUIT */
    public static final Long PASSION_FRUIT = 5L;

    /** PINEAPPLE */
    public static final Long PINEAPPLE = 6L;

    /**
     * Default constructor
     */
    public WSETTropicalFruit() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETTropicalFruit(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseTropicalFruit")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateTropicalFruit")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETTropicalFruit w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETTropicalFruit)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETTropicalFruit(" + id + ")";
    }
}
