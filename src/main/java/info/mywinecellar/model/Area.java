/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Area extends BaseEntity implements Comparable<Area> {

    /**
     * Default Area constructor
     */
    public Area() {
        super();
    }

    /**
     * Area constructor
     *
     * @param name        name
     * @param description description
     * @param weblink     weblink
     */
    public Area(String name, String description, String weblink) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "areas")
    private Set<Region> regions;

    @ManyToMany(mappedBy = "areas")
    private Set<Grape> primaryGrapes;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "area_producer",
            joinColumns =
            @JoinColumn(name = "area_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "producer_id", referencedColumnName = "id")
    )
    private Set<Producer> producers;

    @Override
    public int compareTo(Area a) {
        return name.compareTo(a.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Area)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Area(" + id + ")";
    }
}
