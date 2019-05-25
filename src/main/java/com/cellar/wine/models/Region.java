package com.cellar.wine.models;

import lombok.*;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Region extends BaseEntity implements Comparable<Region> {

    @Builder
    public Region(Long id, String name, String description, String weblink) {
        super(id);
        this.name = name;
        this.description = description;
        this.weblink = weblink;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @ManyToMany
    @JoinTable(name="region_area",
               joinColumns=
               @JoinColumn(name="region_id", referencedColumnName="id"),
               inverseJoinColumns=
               @JoinColumn(name="area_id", referencedColumnName="id")
        )
    private List<Area> areas;

    @Override
    public int compareTo(Region r)
    {
        return name.compareTo(r.getName());
    }
}
