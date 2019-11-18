/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Bottle;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class BottleUI implements Serializable {

    private Long id;
    private Integer number;
    private String location;
    private Boolean show;
    private CountryUI country;
    private RegionUI region;
    private AreaUI area;
    private ProducerUI producer;
    private WineUI wine;

    BottleUI(Bottle b) {
        this.id = b.getId();
        this.number = b.getNumber();
        this.location = b.getLocation();
        this.show = b.getShow();
        this.country = new CountryUI(b.getWine().getProducer().getAreas().get(0).getRegions().get(0).getCountry());
        this.region = new RegionUI(b.getWine().getProducer().getAreas().get(0).getRegions().get(0));
        this.area = new AreaUI(b.getWine().getProducer().getAreas().get(0));
        this.producer = new ProducerUI(b.getWine().getProducer());
        this.wine = new WineUI(b.getWine());
    }
}
