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
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for wine
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class WineDto extends AbstractKeyDto {

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
    private List<?> reviews;
    private List<?> genericTastingNotes;
    private Object closure;
    private Object shape;
    private Object type;
    private Object color;

    /**
     * Default constructor
     */
    public WineDto() {
    }

    /**
     * Constructor
     *
     * @param w The wine
     */
    public WineDto(Wine w) {
        super(toKey(w.getName()));
        this.id = w.getId();
        this.name = w.getName();
        this.vintage = w.getVintage();
        this.size = w.getSize();
        this.description = w.getDescription();
        this.weblink = w.getWeblink();
        this.acid = w.getAcid();
        this.pH = w.getPH();
        this.bottleAging = w.getBottleAging();
        this.alcohol = w.getAlcohol();
        this.subarea = w.getSubarea();
        this.image = Image.encode(w.getImage());
        this.reviews = w.getReviews();
        this.genericTastingNotes = w.getGenericTastingNotes();
        this.closure = w.getClosure();
        this.shape = w.getShape();
        this.type = w.getType();
        this.color = w.getColor();
    }

}
