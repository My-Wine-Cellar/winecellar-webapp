/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.models;

import com.cellar.wine.security.model.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Bottle extends BaseEntity implements Comparable<Bottle> {

    public Bottle() {
        super();
    }

    public Bottle(Integer number, String location, Boolean show, User user, Wine wine) {
        super();
        this.number = number;
        this.location = location;
        this.show = show;
        this.user = user;
        this.wine = wine;
    }

    @NotNull
    @Column(name = "number")
    private Integer number;

    @Column(name = "location", length = 512)
    private String location;

    @Column(name = "show")
    private Boolean show;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(Bottle b) {
        return wine.getName().compareTo(b.getWine().getName());
    }

    @Override
    public String toString() {
        return "Bottle(" + id + ")";
    }
}
