package com.cellar.wine.models;

import com.cellar.wine.security.User;
import lombok.*;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review extends BaseEntity implements Comparable<Review> {

    @Builder
    public Review(Float stars, String comment, Date date, User user, Wine wine) {
        super();
        this.stars = stars;
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.wine = wine;
    }

    @Column(name = "stars")
    private Float stars;

    @Column(name = "comment", length = 512)
    private String comment;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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
}
