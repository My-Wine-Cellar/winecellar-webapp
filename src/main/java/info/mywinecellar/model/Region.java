/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Region extends BaseEntity implements Comparable<Region> {

    /**
     * Default constructor
     */
    public Region() {
        super();
    }

    /**
     * Region constructor
     *
     * @param name        name
     * @param description description
     * @param weblink     weblink
     * @param country     country
     */
    public Region(String name, String description, String weblink, Country country) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
        this.country = country;
    }

    /**
     * Region constructor
     *
     * @param name        String name
     * @param description String description
     * @param weblink     String weblink
     */
    public Region(String name, String description, String weblink) {
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @ManyToMany
    @JoinTable(name = "region_area",
            joinColumns =
            @JoinColumn(name = "region_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "area_id", referencedColumnName = "id")
    )
    private Set<Area> areas;

    @Override
    public int compareTo(Region r) {
        return name.compareTo(r.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Region)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Region(" + id + ")";
    }
}
