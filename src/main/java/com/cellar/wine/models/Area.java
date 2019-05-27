package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Area extends BaseEntity implements Comparable<Area> {

    @Builder
    public Area(Long id, String name, String description, String weblink) {
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

    @ManyToMany(mappedBy = "areas")
    public List<Region> regions;

    @ManyToMany(mappedBy = "areas")
    public List<Grape> primaryGrapes;

    @ManyToMany
    @JoinTable(name = "area_producer",
            joinColumns =
            @JoinColumn(name = "area_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "producer_id", referencedColumnName = "id")
    )
    private List<Producer> producers;

    @Override
    public int compareTo(Area a) {
        return name.compareTo(a.getName());
    }
}
