package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Grape extends BaseEntity implements Comparable<Grape> {

    public Grape() {
        super();
    }

    public Grape(String name, String color, String description, String weblink) {
        super();
        this.name = name;
        this.color = color;
        this.description = description;
        this.weblink = weblink;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @OneToMany
    private List<Grape> alternativeNames;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "grape_area",
            joinColumns =
            @JoinColumn(name = "grape_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "area_id", referencedColumnName = "id")
    )
    private List<Area> areas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grape")
    private List<GrapeComponent> grapeComponents;

    @Override
    public int compareTo(Grape g) {
        return name.compareTo(g.getName());
    }
}
