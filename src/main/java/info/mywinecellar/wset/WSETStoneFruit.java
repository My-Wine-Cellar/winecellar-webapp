/*
 * My-Wine-Cellar, copyright 2022
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
@Table(name = "wset_stone_fruit")
public class WSETStoneFruit extends BaseEntity implements Comparable<WSETStoneFruit> {

    /** PEACH */
    public static final Long PEACH = 1L;

    /** APRICOT */
    public static final Long APRICOT = 2L;

    /** NECTARINE */
    public static final Long NECTARINE = 3L;

    /**
     * Default constructor
     */
    public WSETStoneFruit() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETStoneFruit(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseStoneFruit")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateStoneFruit")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETStoneFruit w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETStoneFruit)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETStoneFruit(" + id + ")";
    }
}
