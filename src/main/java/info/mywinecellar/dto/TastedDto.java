/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Tasted;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Dto for tasted
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class TastedDto implements Serializable {
    private Long id;
    private CountryDto country;
    private RegionDto region;
    private AreaDto area;
    private ProducerDto producer;
    private WineDto wine;
    private Long reviewId;

    /**
     * Constructor
     * @param t The tasted
     * @param reviewId The review id
     */
    public TastedDto(Tasted t, Long reviewId) {
        this.id = t.getId();
        this.country = new CountryDto(t.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionDto(t.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaDto(t.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerDto(t.getWine().getProducer());
        this.wine = new WineDto(t.getWine());
        this.reviewId = reviewId;
    }
}
