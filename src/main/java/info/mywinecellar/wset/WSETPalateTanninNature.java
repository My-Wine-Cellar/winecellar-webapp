/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wset_palate_tannin_nature")
public class WSETPalateTanninNature extends BaseEntity implements Comparable<WSETPalateTanninNature> {

    /** RIPE */
    public static final Long RIPE = 1L;

    /** SOFT */
    public static final Long SOFT = 2L;

    /** SMOOTH */
    public static final Long SMOOTH = 3L;

    /** UNRIPE */
    public static final Long UNRIPE = 4L;

    /** GREEN */
    public static final Long GREEN = 5L;

    /** COARSE */
    public static final Long COARSE = 6L;

    /** STALKY */
    public static final Long STALKY = 7L;

    /** CHALKY */
    public static final Long CHALKY = 8L;

    /** FINE_GRAINED */
    public static final Long FINE_GRAINED = 9L;

    /**
     * Default constructor
     */
    public WSETPalateTanninNature() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETPalateTanninNature(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETPalateTanninNature w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETPalateTanninNature)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETPalateTanninNature(" + id + ")";
    }
}
