/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.models;

import com.cellar.wine.utils.WineSorter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
public class Producer extends BaseEntity implements Comparable<Producer> {

    public Producer() {
        super();
    }

    public Producer(String name, String description, String phone, String fax, String email, String website) {
        super();
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.website = website;
    }

    @NotNull
    @NotEmpty(message = "You must at least provide the name")
    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 8192)
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @ManyToMany(mappedBy = "producers")
    private List<Area> areas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private List<Wine> wines;

    public List<Wine> getWines() {
        Collections.sort(wines, new WineSorter());
        return wines;
    }

    @Override
    public int compareTo(Producer p) {
        return name.compareTo(p.getName());
    }

    public Wine getWine(String label, boolean ignoreNew) {
        label = label.toLowerCase();
        for (Wine wine : wines) {
            if (!ignoreNew || !wine.isNew()) {
                String compName = wine.getName();
                compName = compName.toLowerCase();
                if (compName.equals(label)) {
                    return wine;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Producer(" + id + ")";
    }
}
