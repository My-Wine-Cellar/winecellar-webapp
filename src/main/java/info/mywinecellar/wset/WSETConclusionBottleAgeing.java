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
@Table(name = "wset_conclusion_bottle")
public class WSETConclusionBottleAgeing extends BaseEntity implements Comparable<WSETConclusionBottleAgeing> {

    /** SUITABLE */
    public static final Long SUITABLE = 1L;

    /** NOT_SUITABLE */
    public static final Long NOT_SUITABLE = 2L;

    /**
     * Default constructor
     */
    public WSETConclusionBottleAgeing() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETConclusionBottleAgeing(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETConclusionBottleAgeing w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETConclusionBottleAgeing)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETConclusionBottleAgeing(" + id + ")";
    }
}
