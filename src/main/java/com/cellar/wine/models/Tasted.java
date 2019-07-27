package com.cellar.wine.models;

import com.cellar.wine.security.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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
