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
@Table(name = "wset_red_fruit")
public class WSETRedFruit extends BaseEntity implements Comparable<WSETRedFruit> {

    /** REDCURRANT */
    public static final Long REDCURRANT = 1L;

    /** CRANBERRY */
    public static final Long CRANBERRY = 2L;

    /** RASPBERRY */
    public static final Long RASPBERRY = 3L;

    /** STRAWBERRY */
    public static final Long STRAWBERRY = 4L;

    /** RED_CHERRY */
    public static final Long RED_CHERRY = 5L;

    /** RED_PLUM */
    public static final Long RED_PLUM = 6L;

    /**
     * Default constructor
     */
    public WSETRedFruit() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETRedFruit(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseRedFruit")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateRedFruit")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETRedFruit w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETRedFruit)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETRedFruit(" + id + ")";
    }
}
