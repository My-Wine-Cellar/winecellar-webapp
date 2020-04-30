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
import info.mywinecellar.model.Review;

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
public class ReviewDto implements Serializable {

    private Long id;
    private Float stars;
    private String comment;
    private Date date;
    private CountryDto country;
    private RegionDto region;
    private AreaDto area;
    private ProducerDto producer;
    private WineDto wine;
    private UserDto user;

    /**
     * Constructor
     * @param r The review
     */
    public ReviewDto(Review r) {
        this.id = r.getId();
        this.stars = r.getStars();
        this.comment = r.getComment();
        this.date = r.getDate();

        /* CLEANUP */
        Area ae = r.getWine().getProducer().getAreas().iterator().next();
        Region re = ae.getRegions().iterator().next();

        this.country = new CountryDto(re.getCountry());
        this.region = new RegionDto(re);
        this.area = new AreaDto(ae);
        this.producer = new ProducerDto(r.getWine().getProducer());
        this.wine = new WineDto(r.getWine());
        this.user = new UserDto(r.getUser());
    }
}
