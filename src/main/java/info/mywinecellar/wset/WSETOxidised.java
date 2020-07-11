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
@Table(name = "wset_oxidised")
public class WSETOxidised extends BaseEntity implements Comparable<WSETOxidised> {

    /** ALMOND */
    public static final Long ALMOND = 1L;

    /** HAZELNUT */
    public static final Long HAZELNUT = 2L;

    /** WALNUT */
    public static final Long WALNUT = 3L;

    /** CHOCOLATE */
    public static final Long CHOCOLATE = 4L;

    /** COFFEE */
    public static final Long COFFEE = 5L;

    /** CARAMEL */
    public static final Long CARAMEL = 6L;

    /**
     * Default constructor
     */
    public WSETOxidised() {
    }

    /**
     * Constructor
     *
     * @param name The name
     */
    public WSETOxidised(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "noseOxidised")
    private List<WSET> nose;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "palateOxidised")
    private List<WSET> palate;

    @Override
    public int compareTo(WSETOxidised w) {
        return getId().compareTo(w.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WSETOxidised)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "WSETOxidised(" + id + ")";
    }
}
