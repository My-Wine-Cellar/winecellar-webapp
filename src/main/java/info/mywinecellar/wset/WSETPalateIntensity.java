/*
 * My-Wine-Cellar, copyright 2022
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
@Table(name = "wset_palate_intensity")
public class WSETPalateIntensity extends BaseEntity implements Comparable<WSETPalateIntensity> {

    /** LIGHT */
    public static final Long LIGHT = 1L;

    /** MEDIUM_MINUS */
    public static final Long MEDIUM_MINUS = 2L;

    /** MEDIUM */
    public static final Long MEDIUM = 3L;

    /** MEDIUM_PLUS */
    public static final Long MEDIUM_PLUS = 4L;

    /** PRONOUNCED */
    public static final Long PRONOUNCED = 5L;

    /**
     * Default constructor
     */
    public WSETPalateIntensity() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETPalateIntensity(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETPalateIntensity w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETPalateIntensity)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETPalateIntensity(" + id + ")";
    }
}
