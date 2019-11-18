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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Tasted extends BaseEntity implements Comparable<Tasted> {

    public Tasted() {
        super();
    }

    public Tasted(User user, Wine wine) {
        super();
        this.user = user;
        this.wine = wine;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(Tasted t) {
        return wine.getName().compareTo(t.getWine().getName());
    }

    @Override
    public String toString() {
        return "Tasted(" + id + ")";
    }
}
