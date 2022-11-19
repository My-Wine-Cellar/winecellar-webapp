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
@Table(name = "wset_green_fruit")
public class WSETGreenFruit extends BaseEntity implements Comparable<WSETGreenFruit> {

    /** APPLE */
    public static final Long APPLE = 1L;

    /** PEAR */
    public static final Long PEAR = 2L;

    /** GOOSEBERRY */
    public static final Long GOOSEBERRY = 3L;

    /** GRAPE */
    public static final Long GRAPE = 4L;

    /**
     * Default constructor
     */
    public WSETGreenFruit() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETGreenFruit(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseGreenFruit")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateGreenFruit")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETGreenFruit w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETGreenFruit)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETGreenFruit(" + id + ")";
    }
}
