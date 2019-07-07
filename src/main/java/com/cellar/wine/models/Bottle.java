package com.cellar.wine.models;

import com.cellar.wine.security.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Bottle extends BaseEntity implements Comparable<Bottle> {

    @Builder
    public Bottle(Integer number, String location, Boolean show, User user, Wine wine) {
        super();
        this.number = number;
        this.location = location;
        this.show = show;
        this.user = user;
        this.wine = wine;
    }

    @Column(name = "number")
    private Integer number;

    @Column(name = "location", length = 512)
    private String location;

    @Column(name = "show")
    private Boolean show;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(Bottle b) {
        return wine.getName().compareTo(b.getWine().getName());
    }
}
