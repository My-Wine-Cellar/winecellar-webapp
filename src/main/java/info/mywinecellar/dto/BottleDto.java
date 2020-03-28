/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Bottle;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for bottle
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class BottleDto implements Serializable {

    private Long id;
    private Integer number;
    private String location;
    private Boolean show;
    private CountryDto country;
    private RegionDto region;
    private AreaDto area;
    private ProducerDto producer;
    private WineDto wine;

    /**
     * Constructor
     *
     * @param b The bottle
     */
    public BottleDto(Bottle b) {
        this.id = b.getId();
        this.number = b.getNumber();
        this.location = b.getLocation();
        this.show = b.getShow();
        this.country = new CountryDto(b.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionDto(b.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaDto(b.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerDto(b.getWine().getProducer());
        this.wine = new WineDto(b.getWine());
    }
}
