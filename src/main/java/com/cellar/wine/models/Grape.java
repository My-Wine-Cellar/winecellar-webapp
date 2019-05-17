package com.cellar.wine.models;

import lombok.*;

import java.util.Set;

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
public class Grape extends BaseEntity {

    @Builder
    public Grape(Long id, String name, String color, String description, String alternativeNames, String weblink) {
        super(id);
        this.name = name;
        this.color = color;
        this.description = description;
        this.alternativeNames = alternativeNames;
        this.weblink = weblink;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "alternative_names", length = 8192)
    private String alternativeNames;

    @Column(name = "weblink")
    private String weblink;

    @ManyToMany
    @JoinTable(name="grape_area",
               joinColumns=
               @JoinColumn(name="grape_id", referencedColumnName="id"),
               inverseJoinColumns=
               @JoinColumn(name="area_id", referencedColumnName="id")
        )
    private Set<Area> areas;
}
