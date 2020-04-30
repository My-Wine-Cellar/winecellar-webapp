/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Area;
import info.mywinecellar.model.Region;
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

        /* CLEANUP */
        Area ae = w.getWine().getProducer().getAreas().iterator().next();
        Region re = ae.getRegions().iterator().next();

        this.country = new CountryDto(re.getCountry());
        this.region = new RegionDto(re);
        this.area = new AreaDto(ae);
        this.producer = new ProducerDto(w.getWine().getProducer());
        this.wine = new WineDto(w.getWine());
    }
}
