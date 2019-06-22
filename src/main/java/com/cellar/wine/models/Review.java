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
@AllArgsConstructor
@Entity
public class Review extends BaseEntity implements Comparable<Review> {

    @Builder
    public Review(Long id, Float stars, String comment, User user, Wine wine) {
        super(id);
        this.stars = stars;
        this.comment = comment;
        this.user = user;
        this.wine = wine;
    }

    @Column(name = "stars")
    private Float stars;

    @Column(name = "comment", length = 512)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @Override
    public int compareTo(Review r) {
        return wine.getName().compareTo(r.getWine().getName());
    }
}
