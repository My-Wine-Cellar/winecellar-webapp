package com.cellar.wine.models;

import lombok.*;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grape extends BaseEntity implements Comparable<Grape> {

    @Builder
    public Grape(Long id, String name, String color, String description, String weblink) {
        super(id);
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

    @ManyToMany
    @JoinTable(name="grape_area",
               joinColumns=
               @JoinColumn(name="grape_id", referencedColumnName="id"),
               inverseJoinColumns=
               @JoinColumn(name="area_id", referencedColumnName="id")
        )
    private List<Area> areas;

    @OneToMany(mappedBy = "grape")
    private List<GrapeComponent> grapeComponents;

    @Override
    public int compareTo(Grape g)
    {
        return name.compareTo(g.getName());
    }
}
