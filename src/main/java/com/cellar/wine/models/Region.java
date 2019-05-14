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
public class Region extends BaseEntity {

    @Builder
    public Region(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name="region_area",
               joinColumns=
               @JoinColumn(name="region_id", referencedColumnName="id"),
               inverseJoinColumns=
               @JoinColumn(name="area_id", referencedColumnName="id")
        )
    private Set<Area> areas;
}
