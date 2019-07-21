package com.cellar.wine.ui;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Grape bean
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class GrapeUI implements Serializable {
    private Byte percentage;
    private String name;
    private Long id;
    private Date harvestBegin;
    private Date harvestEnd;
    private Byte macerationDays;
    private Float macerationTemperature;
    private Byte fermentationDays;
    private Float fermentationTemperature;
    private List<BarrelUI> barrels;
    
    public GrapeUI(Byte percentage,
                   String name, Long id,
                   Date harvestBegin, Date harvestEnd,
                   Byte macerationDays, Float macerationTemperature,
                   Byte fermentationDays, Float fermentationTemperature,
                   List<BarrelUI> barrels) {
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

