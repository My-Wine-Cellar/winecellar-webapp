package com.cellar.wine.ui;

import com.cellar.wine.models.Review;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ReviewUI implements Serializable {

    private Long id;
    private Float stars;
    private String comment;
    private Date date;
    private CountryUI country;
    private RegionUI region;
    private AreaUI area;
    private ProducerUI producer;
    private WineUI wine;
    private UserUI user;

    ReviewUI(Review r) {
        this.id = r.getId();
        this.stars = r.getStars();
        this.comment = r.getComment();
        this.date = r.getDate();
        this.country = new CountryUI(r.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionUI(r.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaUI(r.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerUI(r.getWine().getProducer());
        this.wine = new WineUI(r.getWine());
        this.user = new UserUI(r.getUser());
    }
}
