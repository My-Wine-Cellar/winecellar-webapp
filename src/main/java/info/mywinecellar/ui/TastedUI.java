/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Tasted;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * UI for tasted
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class TastedUI implements Serializable {
    private Long id;
    private CountryUI country;
    private RegionUI region;
    private AreaUI area;
    private ProducerUI producer;
    private WineUI wine;
    private Long reviewId;

    /**
     * Constructor
     * @param t The tasted
     * @param reviewId The review id
     */
    TastedUI(Tasted t, Long reviewId) {
        this.id = t.getId();
        this.country = new CountryUI(t.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionUI(t.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaUI(t.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerUI(t.getWine().getProducer());
        this.wine = new WineUI(t.getWine());
        this.reviewId = reviewId;
    }
}
