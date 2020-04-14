/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Wine;
import info.mywinecellar.util.Image;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class WineDetailsDto extends AbstractKeyDto {

    private Long id;
    private String name;
    private Integer vintage;
    private Float size;
    private String description;
    private String weblink;
    private String image;
    private Float alcohol;
    private Float acid;
    private Float pH;
    private Integer bottleAging;
    private String subarea;
    private List<Long> reviews;
    private List<Long> genericTastingNotes;
    private ColorDto color;
    private TypeDto type;
    private ShapeDto shape;
    private ClosureDto closure;
    private ProducerDto producer;

    /**
     * Default constructor
     */
    public WineDetailsDto() {
    }

    /**
     * Constructor
     *
     * @param w Wine w
     */
    public WineDetailsDto(Wine w) {
        super(toKey(w.getName()));
        this.id = w.getId();
        this.name = w.getName();
        this.vintage = w.getVintage();
        this.size = w.getSize();
        this.description = w.getDescription();
        this.weblink = w.getWeblink();
        this.image = Image.encode(w.getImage());
        this.acid = w.getAcid();
        this.pH = w.getPH();
        this.bottleAging = w.getBottleAging();
        this.alcohol = w.getAlcohol();
        this.subarea = w.getSubarea();
        this.producer = new ProducerDto(w.getProducer());
        this.closure = new ClosureDto(w.getClosure());
        this.shape = new ShapeDto(w.getShape());
        this.type = new TypeDto(w.getType());
        this.color = new ColorDto(w.getColor());
    }

}
