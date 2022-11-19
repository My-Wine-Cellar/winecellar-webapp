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
@Table(name = "wset_malo")
public class WSETMalo extends BaseEntity implements Comparable<WSETMalo> {

    /** BUTTER */
    public static final Long BUTTER = 1L;

    /** CREAM */
    public static final Long CREAM = 2L;

    /** CHEESE */
    public static final Long CHEESE = 3L;

    /**
     * Default constructor
     */
    public WSETMalo() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETMalo(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseMalo")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateMalo")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETMalo w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETMalo)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETMalo(" + id + ")";
    }
}
