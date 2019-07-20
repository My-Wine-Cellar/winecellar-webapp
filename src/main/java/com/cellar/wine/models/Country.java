package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@Entity
public class Country extends BaseEntity implements Comparable<Country> {

    public Country() {
        super();
    }

    public Country(String name, String description, String weblink) {
        super();
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

    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @Override
    public int compareTo(Country c) {
        return name.compareTo(c.getName());
    }
}
