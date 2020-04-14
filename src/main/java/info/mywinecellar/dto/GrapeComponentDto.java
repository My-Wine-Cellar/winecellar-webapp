/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.GrapeComponent;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for grape_component
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
public class GrapeComponentDto extends AbstractKeyDto {

    private Long id;

    // Grape name
    private String name;

    @Max(100)
    @Digits(integer = 3, fraction = 0)
    @NotNull
    private Byte percentage;

    private Date harvestBegin;
    private Date harvestEnd;

    @NotNull
    private Long grapeId;

    private Long wineId;

    private Long fermentationId;
    private Long macerationId;

    @Digits(integer = 3, fraction = 0)
    private Byte macerationDays;

    @Digits(integer = 2, fraction = 1)
    private Float macerationTemperature;

    @Digits(integer = 3, fraction = 0)
    private Byte fermentationDays;

    @Digits(integer = 2, fraction = 1)
    private Float fermentationTemperature;

    private List<BarrelDto> barrels;

    /**
     * Default constructor
     */
    public GrapeComponentDto() {
    }

    /**
     * Constructor
     *
     * @param gc grapeComponent
     */
    public GrapeComponentDto(GrapeComponent gc) {
        this.id = gc.getId();
        this.percentage = gc.getPercentage();
        this.harvestBegin = gc.getHarvestBegin();
        this.harvestEnd = gc.getHarvestEnd();
        this.grapeId = gc.getGrape().getId();
        this.wineId = gc.getWine().getId();
    }

    /**
     * Constructor
     *
     * @param percentage              The percentage
     * @param name                    The grape name
     * @param id                      The identifier
     * @param harvestBegin            The harvest begin date
     * @param harvestEnd              The harvest end date
     * @param macerationDays          The number of maceration days
     * @param macerationTemperature   The maceration temperature
     * @param fermentationDays        The number of fermentation days
     * @param fermentationTemperature The fermentation temperature
     * @param barrels                 The barrels
     */
    public GrapeComponentDto(Byte percentage,
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

}
