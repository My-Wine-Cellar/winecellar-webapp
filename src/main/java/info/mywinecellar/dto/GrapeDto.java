/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Grape;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for grape
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class GrapeDto extends AbstractKeyDto {

    private Long id;
    private String name;
    private String color;
    private String alternativeNames;
    private Byte percentage;
    private Date harvestBegin;
    private Date harvestEnd;
    private Byte macerationDays;
    private Float macerationTemperature;
    private Byte fermentationDays;
    private Float fermentationTemperature;
    private List<BarrelDto> barrels;

    @Size(max = 255)
    private String description;

    @Size(max = 50)
    private String weblink;

    /**
     * Default constructor
     */
    public GrapeDto() {
    }

    /**
     * Constructor
     * @param percentage The percentage
     * @param name The name
     * @param id The identifier
     * @param harvestBegin The harvest begin date
     * @param harvestEnd The harvest end date
     * @param macerationDays The number of maceration days
     * @param macerationTemperature The maceration temperature
     * @param fermentationDays The number of fermentation days
     * @param fermentationTemperature The fermentation temperature
     * @param barrels The barrels
     */
    public GrapeDto(Byte percentage,
                    String name, Long id,
                    Date harvestBegin, Date harvestEnd,
                    Byte macerationDays, Float macerationTemperature,
                    Byte fermentationDays, Float fermentationTemperature,
                    List<BarrelDto> barrels) {
        super(toKey(name));

        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.harvestBegin = harvestBegin;
        this.harvestEnd = harvestEnd;

        this.macerationDays = macerationDays;
        this.macerationTemperature = macerationTemperature;

        this.fermentationDays = fermentationDays;
        this.fermentationTemperature = fermentationTemperature;

        this.barrels = barrels;
    }

    /**
     * Constructor
     * @param g The grape
     */
    public GrapeDto(Grape g) {
        super(toKey(g.getName()));

        this.id = g.getId();
        this.name = g.getName();
        this.color = g.getColor();
        this.description = g.getDescription();
        this.weblink = g.getWeblink();
    }
}
