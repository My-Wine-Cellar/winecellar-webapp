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
@Table(name = "wset_herbal")
public class WSETHerbal extends BaseEntity implements Comparable<WSETHerbal> {

    /** EUCALYPTUS */
    public static final Long EUCALYPTUS = 1L;

    /** MINT */
    public static final Long MINT = 2L;

    /** FENNEL */
    public static final Long FENNEL = 3L;

    /** DILL */
    public static final Long DILL = 4L;

    /** DRIED_HERBS */
    public static final Long DRIED_HERBS = 5L;

    /**
     * Default constructor
     */
    public WSETHerbal() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETHerbal(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseHerbal")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateHerbal")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETHerbal w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETHerbal)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETHerbal(" + id + ")";
    }
}
