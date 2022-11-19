/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.wset;

import info.mywinecellar.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wset_conclusion_quality")
public class WSETConclusionQuality extends BaseEntity implements Comparable<WSETConclusionQuality> {

    /** POOR */
    public static final Long POOR = 1L;

    /** ACCEPTABLE */
    public static final Long ACCEPTABLE = 2L;

    /** GOOD */
    public static final Long GOOD = 3L;

    /** VERY_GOOD */
    public static final Long VERY_GOOD = 4L;

    /** OUTSTANDING */
    public static final Long OUTSTANDING = 5L;

    /**
     * Default constructor
     */
    public WSETConclusionQuality() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETConclusionQuality(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETConclusionQuality w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETConclusionQuality)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETConclusionQuality(" + id + ")";
    }
}
