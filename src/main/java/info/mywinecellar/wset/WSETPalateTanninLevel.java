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
@Table(name = "wset_palate_tannin_level")
public class WSETPalateTanninLevel extends BaseEntity implements Comparable<WSETPalateTanninLevel> {

    /** LOW */
    public static final Long LOW = 1L;

    /** MEDIUM_MINUS */
    public static final Long MEDIUM_MINUS = 2L;

    /** MEDIUM */
    public static final Long MEDIUM = 3L;

    /** MEDIUM_PLUS */
    public static final Long MEDIUM_PLUS = 4L;

    /** HIGH */
    public static final Long HIGH = 5L;

    /**
     * Default constructor
     */
    public WSETPalateTanninLevel() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETPalateTanninLevel(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETPalateTanninLevel w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETPalateTanninLevel)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETPalateTanninLevel(" + id + ")";
    }
}
