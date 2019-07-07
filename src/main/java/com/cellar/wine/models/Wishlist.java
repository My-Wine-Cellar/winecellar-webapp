package com.cellar.wine.models;

import com.cellar.wine.security.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wishlist extends BaseEntity implements Comparable<Wishlist> {

    @Builder
    public Wishlist(Long id, Date date, User user, Wine wine) {
        super(id);
        this.date = date;
        this.user = user;
        this.wine = wine;
    }

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(Wishlist w) {
        return wine.getName().compareTo(w.getWine().getName());
    }
}
