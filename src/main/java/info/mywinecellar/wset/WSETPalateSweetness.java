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
@Table(name = "wset_palate_sweetness")
public class WSETPalateSweetness extends BaseEntity implements Comparable<WSETPalateSweetness> {

    /** DRY */
    public static final Long DRY = 1L;

    /** OFF_DRY */
    public static final Long OFF_DRY = 2L;

    /** MEDIUM_DRY */
    public static final Long MEDIUM_DRY = 3L;

    /** MEDIUM_SWEET */
    public static final Long MEDIUM_SWEET = 4L;

    /** SWEET */
    public static final Long SWEET = 5L;

    /**
     * Default constructor
     */
    public WSETPalateSweetness() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETPalateSweetness(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETPalateSweetness w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETPalateSweetness)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETPalateSweetness(" + id + ")";
    }
}
