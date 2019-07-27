package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Region extends BaseEntity implements Comparable<Region> {

    public Region() {
        super();
    }

    public Region(String name, String description, String weblink, Country country) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
        this.country = country;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

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
