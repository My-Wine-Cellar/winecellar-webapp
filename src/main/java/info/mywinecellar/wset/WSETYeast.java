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
@Table(name = "wset_yeast")
public class WSETYeast extends BaseEntity implements Comparable<WSETYeast> {

    /** BISCUIT */
    public static final Long BISCUIT = 1L;

    /** PASTRY */
    public static final Long PASTRY = 2L;

    /** BREAD */
    public static final Long BREAD = 3L;

    /** TOASTED_BREAD */
    public static final Long TOASTED_BREAD = 4L;

    /** BREAD_DOUGH */
    public static final Long BREAD_DOUGH = 5L;

    /** BRIOCHE */
    public static final Long BRIOCHE = 6L;

    /** CHEESE */
    public static final Long CHEESE = 7L;

    /** YOGURT */
    public static final Long YOGURT = 8L;

    /** ACETALDEHYDE */
    public static final Long ACETALDEHYDE = 9L;

    /**
     * Default constructor
     */
    public WSETYeast() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETYeast(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseYeast")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateYeast")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETYeast w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETYeast)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETYeast(" + id + ")";
    }
}
