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
@Table(name = "wset_black_fruit")
public class WSETBlackFruit extends BaseEntity implements Comparable<WSETBlackFruit> {

    /** BLACKCURRANT */
    public static final Long BLACKCURRANT = 1L;

    /** BLACKBERRY */
    public static final Long BLACKBERRY = 2L;

    /** BLUEBERRY */
    public static final Long BLUEBERRY = 3L;

    /** BLACK_CHERRY */
    public static final Long BLACK_CHERRY = 4L;

    /** BLACK_PLUM */
    public static final Long BLACK_PLUM = 5L;

    /**
     * Default constructor
     */
    public WSETBlackFruit() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETBlackFruit(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseBlackFruit")
    private Set<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateBlackFruit")
    private Set<WSET> palate;

    @Override
    public int compareTo(WSETBlackFruit w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETBlackFruit)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETBlackFruit(" + id + ")";
    }
}
