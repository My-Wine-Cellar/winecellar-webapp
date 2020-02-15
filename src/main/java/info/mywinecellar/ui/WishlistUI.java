/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Wishlist;

import java.io.Serializable;
import java.sql.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UI for wishlist
 */
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

    /**
     * Constructor
     * @param w The wishlist
     */
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
