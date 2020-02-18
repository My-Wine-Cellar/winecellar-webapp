/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import info.mywinecellar.util.ProducerSorter;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Area extends BaseEntity implements Comparable<Area> {

    /**
     * Default Area constructor
     */
    public Area() {
        super();
    }

    /**
     * Area constructor
     *
     * @param name        name
     * @param description description
     * @param weblink     weblink
     */
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "areas")
    private List<Region> regions;

    @JsonIgnore
    @ManyToMany(mappedBy = "areas")
    private List<Grape> primaryGrapes;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "area_producer",
            joinColumns =
            @JoinColumn(name = "area_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "producer_id", referencedColumnName = "id")
    )
    private List<Producer> producers;

    /**
     * Sort Producers using ProducerSorter
     *
     * @return sorted Producers
     */
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
