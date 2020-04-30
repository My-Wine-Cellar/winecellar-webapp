/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Grape extends BaseEntity implements Comparable<Grape> {

    /**
     * Default constructor
     */
    public Grape() {
        super();
    }

    /**
     * Grape constructor
     *
     * @param name        name
     * @param color       color
     * @param description description
     * @param weblink     weblink
     */
    public Grape(String name, String color, String description, String weblink) {
        super();
        this.name = name;
        this.color = color;
        this.description = description;
        this.weblink = weblink;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "color")
    private String color;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "weblink")
    private String weblink;

    @OneToMany
    private Set<Grape> alternativeNames;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "grape_area",
            joinColumns =
            @JoinColumn(name = "grape_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "area_id", referencedColumnName = "id")
    )
    private Set<Area> areas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grape")
    private Set<GrapeComponent> grapeComponents;

    @Override
    public int compareTo(Grape g) {
        return name.compareTo(g.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Grape)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Grape(" + id + ")";
    }
}
