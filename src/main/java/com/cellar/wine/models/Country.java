package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country extends BaseEntity {

    @Builder
    public Country(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    private Set<Region> regions;
}
