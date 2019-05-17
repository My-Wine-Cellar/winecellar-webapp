package com.cellar.wine.models;

import lombok.*;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Area extends BaseEntity {

    @Builder
    public Area(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy="areas")
    public Set<Region> regions;

    @ManyToMany(mappedBy="areas")
    public Set<Grape> primaryGrapes;
}
