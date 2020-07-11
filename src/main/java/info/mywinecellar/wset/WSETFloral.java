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
@Table(name = "wset_floral")
public class WSETFloral extends BaseEntity implements Comparable<WSETFloral> {

    /** BLOSSOM */
    public static final Long BLOSSOM = 1L;

    /** ELDERFLOWER */
    public static final Long ELDERFLOWER = 2L;

    /** HONEYSUCKLE */
    public static final Long HONEYSUCKLE = 3L;

    /** JASMINE */
    public static final Long JASMINE = 4L;

    /** ROSE */
    public static final Long ROSE = 5L;

    /** VIOLET */
    public static final Long VIOLET = 6L;

    /**
     * Default constructor
     */
    public WSETFloral() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETFloral(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseFloral")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateFloral")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETFloral w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETFloral)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETFloral(" + id + ")";
    }
}
