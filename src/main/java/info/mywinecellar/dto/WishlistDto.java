/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Wishlist;

import java.io.Serializable;
import java.sql.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for wishlist
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class WishlistDto implements Serializable {

    private Long id;
    private Date date;
    private CountryDto country;
    private RegionDto region;
    private AreaDto area;
    private ProducerDto producer;
    private WineDto wine;

    /**
     * Constructor
     * @param w The wishlist
     */
    public WishlistDto(Wishlist w) {
        this.id = w.getId();
        this.date = w.getDate();
        this.country = new CountryDto(w.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionDto(w.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaDto(w.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerDto(w.getWine().getProducer());
        this.wine = new WineDto(w.getWine());
    }
}
