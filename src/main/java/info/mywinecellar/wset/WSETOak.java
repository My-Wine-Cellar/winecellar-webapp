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
@Table(name = "wset_oak")
public class WSETOak extends BaseEntity implements Comparable<WSETOak> {

    /** VANILLA */
    public static final Long VANILLA = 1L;

    /** CLOVES */
    public static final Long CLOVES = 2L;

    /** COCONUT */
    public static final Long COCONUT = 3L;

    /** CEDAR */
    public static final Long CEDAR = 4L;

    /** CHARRED_WOOD */
    public static final Long CHARRED_WOOD = 5L;

    /** SMOKE */
    public static final Long SMOKE = 6L;

    /** CHOCOLATE */
    public static final Long CHOCOLATE = 7L;

    /** COFFEE */
    public static final Long COFFEE = 8L;

    /**
     * Default constructor
     */
    public WSETOak() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETOak(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseOak")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateOak")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETOak w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETOak)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETOak(" + id + ")";
    }
}
