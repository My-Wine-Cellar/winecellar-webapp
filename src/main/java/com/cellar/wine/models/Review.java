package com.cellar.wine.models;

import com.cellar.wine.security.model.User;
import lombok.*;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Review extends BaseEntity implements Comparable<Review> {

    public Review() {
        super();
    }

    public Review(Float stars, String comment, Date date, User user, Wine wine) {
        super();
        this.stars = stars;
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.wine = wine;
    }

    @NotNull
    @Column(name = "stars")
    private Float stars;

    @Column(name = "comment", length = 512)
    private String comment;

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
    public int compareTo(Review r) {
        int result;

        if (getId().equals(r.getId()))
            return 0;

        result = getStars().compareTo(r.getStars());
        if (result != 0)
            return -result;

        return wine.getName().compareTo(r.getWine().getName());
    }

    @Override
    public String toString() {
        return "Review(" + id + ")";
    }
}
