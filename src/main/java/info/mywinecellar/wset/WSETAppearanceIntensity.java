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
@Table(name = "wset_appearance_intensity")
public class WSETAppearanceIntensity extends BaseEntity implements Comparable<WSETAppearanceIntensity> {

    /** PALE */
    public static final Long PALE = 1L;

    /** MEDIUM */
    public static final Long MEDIUM = 2L;

    /** DEEP */
    public static final Long DEEP = 3L;

    /**
     * Default constructor
     */
    public WSETAppearanceIntensity() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETAppearanceIntensity(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(WSETAppearanceIntensity w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETAppearanceIntensity)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETAppearanceIntensity(" + id + ")";
    }
}
