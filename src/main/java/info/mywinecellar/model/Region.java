/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;


import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
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
    private List<Area> areas;

    /**
     * This will find the Area with the same name as the Region at the top of list
     *
     * @return areas
     */
    public List<Area> getAreas() {
        areas.forEach(area -> {
            if (area.getName().equals(this.name)) {
                Collections.swap(areas, areas.indexOf(area), 0);
            }
        });
        return areas;
    }

    @Override
    public int compareTo(Region r) {
        return name.compareTo(r.getName());
    }

    @Override
    public String toString() {
        return "Region(" + id + ")";
    }
}
