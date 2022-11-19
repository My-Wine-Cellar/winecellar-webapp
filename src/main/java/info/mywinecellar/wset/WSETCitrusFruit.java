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
@Table(name = "wset_citrus_fruit")
public class WSETCitrusFruit extends BaseEntity implements Comparable<WSETCitrusFruit> {

    /** GRAPEFRUIT */
    public static final Long GRAPEFRUIT = 1L;

    /** LEMON */
    public static final Long LEMON = 2L;

    /** LIME */
    public static final Long LIME = 3L;

    /** ORANGE */
    public static final Long ORANGE = 4L;

    /**
     * Default constructor
     */
    public WSETCitrusFruit() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETCitrusFruit(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseCitrusFruit")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateCitrusFruit")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETCitrusFruit w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETCitrusFruit)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETCitrusFruit(" + id + ")";
    }
}
