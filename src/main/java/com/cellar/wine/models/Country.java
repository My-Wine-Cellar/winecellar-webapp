package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedNativeQuery(name = "getRegions", query = "select * from country c join country_regions cr on c.id = cr.country_id")
public class Country extends BaseEntity {

    @Builder
    public Country(Long id, String name, String description, String weblink) {
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

    @OneToMany
    private Set<Region> regions;

}
