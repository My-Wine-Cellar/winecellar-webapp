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
@Table(name = "wset_palate_body")
public class WSETPalateBody extends BaseEntity implements Comparable<WSETPalateBody> {

    /** LOW */
    public static final Long LOW = 1L;

    /** MEDIUM_MINUS */
    public static final Long MEDIUM_MINUS = 2L;

    /** MEDIUM */
    public static final Long MEDIUM = 3L;

    /** MEDIUM_PLUS */
    public static final Long MEDIUM_PLUS = 4L;

    /** FULL */
    public static final Long FULL = 5L;

    /**
     * Default constructor
     */
    public WSETPalateBody() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETPalateBody(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETPalateBody w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETPalateBody)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETPalateBody(" + id + ")";
    }
}
