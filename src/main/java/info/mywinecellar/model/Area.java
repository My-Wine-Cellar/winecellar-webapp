/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import info.mywinecellar.util.ProducerSorter;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Area extends BaseEntity implements Comparable<Area> {

    public Area() {
        super();
    }

    public Area(String name, String description, String weblink) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "areas")
    public List<Region> regions;

    @ManyToMany(mappedBy = "areas")
    public List<Grape> primaryGrapes;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "area_producer",
               joinColumns =
               @JoinColumn(name = "area_id", referencedColumnName = "id"),
               inverseJoinColumns =
               @JoinColumn(name = "producer_id", referencedColumnName = "id")
    )
    private List<Producer> producers;

    public List<Producer> getProducers() {
        Collections.sort(producers, new ProducerSorter());
        return producers;
    }

    @Override
    public int compareTo(Area a) {
        return name.compareTo(a.getName());
    }

    @Override
    public String toString() {
        return "Area(" + id + ")";
    }
}
