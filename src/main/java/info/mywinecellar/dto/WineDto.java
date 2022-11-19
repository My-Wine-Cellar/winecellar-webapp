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
import info.mywinecellar.util.VintageMax;
import info.mywinecellar.wset.WSET;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Size(max = 255)
    @NotEmpty
    private String name;

    @Min(1800)
    @VintageMax
    @NotNull
    private Integer vintage;

    @Max(18)
    @Digits(integer = 2, fraction = 3)
    @NotNull
    private Float size;

    @Size(max = 8192)
    private String description;

    @Size(max = 255)
    private String weblink;

    private String image;

    @Max(50)
    @Min(10)
    @Digits(integer = 2, fraction = 1)
    private Float alcohol;

    @Digits(integer = 2, fraction = 1)
    private Float acid;

    @Max(14)
    @Min(1)
    private Float pH;

    @Min(1)
    private Integer bottleAging;

    @Size(max = 255)
    private String subarea;

    private Long colorId;
    private Long typeId;
    private Long shapeId;
    private Long closureId;
    private Long producerId;
    private List<Long> wset;

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
        this.image = Image.encode(w.getImage());
        this.acid = w.getAcid();
        this.pH = w.getPH();
        this.bottleAging = w.getBottleAging();
        this.alcohol = w.getAlcohol();
        this.subarea = w.getSubarea();
        this.producerId = w.getProducer().getId();
        this.closureId = w.getClosure().getId();
        this.shapeId = w.getShape().getId();
        this.typeId = w.getType().getId();
        this.colorId = w.getColor().getId();
        this.wset = new ArrayList<>();

        if (w.getWset() != null) {
            for (WSET ws : w.getWset()) {
                wset.add(ws.getId());
            }
        }
    }

    /**
     * Check if Id is new
     *
     * @return true
     */
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}
