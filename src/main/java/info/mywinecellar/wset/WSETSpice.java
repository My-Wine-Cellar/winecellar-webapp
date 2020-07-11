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
@Table(name = "wset_spice")
public class WSETSpice extends BaseEntity implements Comparable<WSETSpice> {

    /** BLACK_WHITE_PEPPER */
    public static final Long BLACK_WHITE_PEPPER = 1L;

    /** LIQUORICE */
    public static final Long LIQUORICE = 2L;

    /** CINNAMON */
    public static final Long CINNAMON = 3L;

    /**
     * Default constructor
     */
    public WSETSpice() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETSpice(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseSpice")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateSpice")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETSpice w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETSpice)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETSpice(" + id + ")";
    }
}
