/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Barrel extends BaseEntity implements Comparable<Barrel> {

    public Barrel() {
        super();
    }

    public Barrel(String name, String description, String weblink) {
        super();
        this.name = name;
        this.description = description;
        this.weblink = weblink;
    }

    @NotNull
    @Column(name = "name")
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", length = 8192)
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "weblink")
    private String weblink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barrel")
    private List<BarrelComponent> barrelComponents;

    @Override
    public int compareTo(Barrel b) {
        return name.compareTo(b.getName());
    }

    @Override
    public String toString() {
        return "Barrel(" + id + ")";
    }
}
