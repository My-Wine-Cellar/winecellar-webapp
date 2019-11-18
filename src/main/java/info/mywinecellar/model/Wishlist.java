/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import info.mywinecellar.security.model.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Wishlist extends BaseEntity implements Comparable<Wishlist> {

    public Wishlist() {
        super();
    }

    public Wishlist(Date date, User user, Wine wine) {
        super();
        this.date = date;
        this.user = user;
        this.wine = wine;
    }

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(Wishlist w) {
        return wine.getName().compareTo(w.getWine().getName());
    }

    @Override
    public String toString() {
        return "Wishlist(" + id + ")";
    }
}
