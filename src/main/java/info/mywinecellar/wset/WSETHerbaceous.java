/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;

import java.util.Set;

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
@Table(name = "wset_herbaceous")
public class WSETHerbaceous extends BaseEntity implements Comparable<WSETHerbaceous> {

    /** GREEN_BELL_PEPPER */
    public static final Long GREEN_BELL_PEPPER = 1L;

    /** GRASS */
    public static final Long GRASS = 2L;

    /** TOMATO_LEAF */
    public static final Long TOMATO_LEAF = 3L;

    /** ASPARAGUS */
    public static final Long ASPARAGUS = 4L;

    /**
     * Default constructor
     */
    public WSETHerbaceous() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETHerbaceous(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseHerbaceous")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateHerbaceous")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETHerbaceous w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETHerbaceous)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETHerbaceous(" + id + ")";
    }
}
