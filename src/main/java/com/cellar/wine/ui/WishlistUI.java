package com.cellar.wine.ui;

import com.cellar.wine.models.Wishlist;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class WishlistUI implements Serializable {

    private Long id;
    private Date date;
    private CountryUI country;
    private RegionUI region;
    private AreaUI area;
    private ProducerUI producer;
    private WineUI wine;

    WishlistUI(Wishlist w) {
        this.id = w.getId();
        this.date = w.getDate();
        this.country = new CountryUI(w.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionUI(w.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaUI(w.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerUI(w.getWine().getProducer());
        this.wine = new WineUI(w.getWine());
    }
}
