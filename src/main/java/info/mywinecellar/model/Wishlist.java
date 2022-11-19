/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Wishlist extends BaseEntity implements Comparable<Wishlist> {

    /**
     * Default constructor
     */
    public Wishlist() {
        super();
    }

    /**
     * Wishlist constructor
     *
     * @param date date
     * @param user user
     * @param wine wine
     */
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Wishlist)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Wishlist(" + id + ")";
    }
}
