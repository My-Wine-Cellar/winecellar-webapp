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
@Table(name = "wset_fruit_ripeness")
public class WSETFruitRipeness extends BaseEntity implements Comparable<WSETFruitRipeness> {

    /** UNRIPE_FRUIT */
    public static final Long UNRIPE_FRUIT = 1L;

    /** RIPE_FRUIT */
    public static final Long RIPE_FRUIT = 2L;

    /** DRIED_FRUIT */
    public static final Long DRIED_FRUIT = 3L;

    /** COOKED_FRUIT */
    public static final Long COOKED_FRUIT = 4L;

    /**
     * Default constructor
     */
    public WSETFruitRipeness() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETFruitRipeness(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseFruitRipeness")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateFruitRipeness")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETFruitRipeness w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETFruitRipeness)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETFruitRipeness(" + id + ")";
    }
}
